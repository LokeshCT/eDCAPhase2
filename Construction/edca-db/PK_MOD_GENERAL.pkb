CREATE OR REPLACE PACKAGE BODY MDSLD.PK_MOD_GENERAL
AS
   PROCEDURE dca_convert_order (p_i_scenario_id         IN     VARCHAR2,
                                p_i_inflight_order_id   IN     NUMBER,
                                p_i_earlier_order_id    IN     NUMBER,
                                p_i_user_id             IN     NUMBER,
                                p_i_team_category_id    IN     NUMBER,
                                p_o_email_id               OUT VARCHAR2,
                                p_o_error_desc             OUT VARCHAR2,
                                p_i_team_id             IN     NUMBER)
   AS
      vEmailId             VARCHAR2 (100);
      vErrorDesc           VARCHAR2 (1000);
      vCount               NUMBER (5);
      vInventory_OrderId   NUMBER (20);
   BEGIN
      --Convert the order based on the scenario.
      IF (UPPER (p_i_scenario_id) = 'SCENARIO1')
      THEN
         --Scenario 1 - Convert Inflight to Provide.
         UPDATE DDDCA_ORDER_DETAILS
            SET MOD_STATUS = NULL, INFLIGHT_SCENARIO_ID = NULL
          WHERE ORDER_ID = p_i_inflight_order_id;

         UPDATE DDDCA_CUSTOMER
            SET ORDERTYPE = 'Add'
          WHERE ORDER_ID = p_i_inflight_order_id;

         UPDATE DDDCA_SITE_SERVICE_INSTANCE
            SET INF_INST_ORDERTYPE = 'Add', SER_INST_ORDERTYPE = 'Add'
          WHERE SERVICEINSTANCEID IN (SELECT DDDCA_SITE_SERVICE_INSTANCE.SERVICEINSTANCEID
                                        FROM DDDCA_SITE,
                                             DDDCA_SITE_LOCATION,
                                             DDDCA_SITE_SERVICE_INSTANCE
                                       WHERE     DDDCA_SITE.ORDER_ID =
                                                    p_i_inflight_order_id
                                             AND DDDCA_SITE_LOCATION.SITE_ID =
                                                    DDDCA_SITE.SITE_ID
                                             AND DDDCA_SITE_SERVICE_INSTANCE.SITE_LOCATION_ID =
                                                    DDDCA_SITE_LOCATION.SITE_LOCATION_ID);

         COMMIT;
         p_o_error_desc := 'OK';
      ELSIF (UPPER (p_i_scenario_id) = 'SCENARIO2')
      THEN
         --Scenario 2 - Convert Inflight to Provide and Cancel the earlier Provide.
         UPDATE DDDCA_ORDER_DETAILS
            SET MOD_STATUS = NULL, INFLIGHT_SCENARIO_ID = NULL
          WHERE ORDER_ID = p_i_inflight_order_id;

         UPDATE DDDCA_CUSTOMER
            SET ORDERTYPE = 'Add'
          WHERE ORDER_ID = p_i_inflight_order_id;

         UPDATE DDDCA_SITE_SERVICE_INSTANCE
            SET INF_INST_ORDERTYPE = 'Add', SER_INST_ORDERTYPE = 'Add'
          WHERE SERVICEINSTANCEID IN (SELECT DDDCA_SITE_SERVICE_INSTANCE.SERVICEINSTANCEID
                                        FROM DDDCA_SITE,
                                             DDDCA_SITE_LOCATION,
                                             DDDCA_SITE_SERVICE_INSTANCE
                                       WHERE     DDDCA_SITE.ORDER_ID =
                                                    p_i_inflight_order_id
                                             AND DDDCA_SITE_LOCATION.SITE_ID =
                                                    DDDCA_SITE.SITE_ID
                                             AND DDDCA_SITE_SERVICE_INSTANCE.SITE_LOCATION_ID =
                                                    DDDCA_SITE_LOCATION.SITE_LOCATION_ID);


         SELECT COUNT (ORDEROWNER)
           INTO vCount
           FROM DDDCA_ORDER_DETAILS
          WHERE ORDER_ID = p_i_earlier_order_id;

         IF vCount = 0
         THEN                                   ---If it is a unassigned order
            UPDATE DDDCA_ORDER_DETAILS
               SET ORDERSTATUS = 'CANCELED ORDER',
                   CANCELEDBY = p_i_user_id,
                   CANCELREASON =
                      'Inflight for Provide order converted to Provide order, hence this earlier Provide order is cancelled.',
                   CANCELEDBYTEAM = p_i_team_id,
                   DELEGATEDTO = NULL,
                   DELEGATEDBY = NULL,
                   DELEGATEREASON = NULL,
                   DELEGATEDTOTEAMID = NULL,
                   DELEGATEDBYTEAMID = NULL
             WHERE ORDER_ID = p_i_earlier_order_id;

            vEmailId := NULL;
            vErrorDesc := 'OK-Previous order unassigned';
         ELSE
            --Submit the earlier Provide to Cancel queue.
            DDDCA_PK_USER_PROFILE.DDDCA_sp_SaveReason (
               'CANCEL',
               p_i_user_id,
               'Inflight for Provide order converted to Provide order, hence this earlier Provide order is cancelled.',
               p_i_earlier_order_id,
               p_i_team_category_id,
               vEmailId,
               vErrorDesc,
               p_i_team_id);
         END IF;

         p_o_email_id := vEmailId;
         p_o_error_desc := vErrorDesc;

         IF vErrorDesc = 'OK' OR vErrorDesc = 'OK-Previous order unassigned'
         THEN
            COMMIT;
         ELSE
            ROLLBACK;
         END IF;
      ELSIF (UPPER (p_i_scenario_id) = 'SCENARIO4')
      THEN
         --Scenario 4 - Convert Inflight to Modify.
         --gET THE INVENTORY ORDER id FROM THE INFLIGHT ORDER id.

         SELECT INV_ORDER_ID
           INTO vInventory_OrderId
           FROM DDDCA_MOD_ORDER
          WHERE DCOMP_ORDER_ID = p_i_inflight_order_id;

         UPDATE DDDCA_ORDER_DETAILS
            SET INFLIGHT_SCENARIO_ID = NULL
          WHERE ORDER_ID IN (p_i_inflight_order_id, vInventory_OrderId);

         UPDATE DDDCA_CUSTOMER
            SET ORDERTYPE = 'Modify'
          WHERE ORDER_ID IN (p_i_inflight_order_id, vInventory_OrderId);

         UPDATE DDDCA_SITE_SERVICE_INSTANCE
            SET INF_INST_ORDERTYPE = 'Modify', SER_INST_ORDERTYPE = 'Modify'
          WHERE SERVICEINSTANCEID IN (SELECT DDDCA_SITE_SERVICE_INSTANCE.SERVICEINSTANCEID
                                        FROM DDDCA_SITE,
                                             DDDCA_SITE_LOCATION,
                                             DDDCA_SITE_SERVICE_INSTANCE
                                       WHERE     DDDCA_SITE.ORDER_ID IN (p_i_inflight_order_id,
                                                                         vInventory_OrderId)
                                             AND DDDCA_SITE_LOCATION.SITE_ID =
                                                    DDDCA_SITE.SITE_ID
                                             AND DDDCA_SITE_SERVICE_INSTANCE.SITE_LOCATION_ID =
                                                    DDDCA_SITE_LOCATION.SITE_LOCATION_ID);

         COMMIT;
         p_o_error_desc := 'OK';
      ELSIF (UPPER (p_i_scenario_id) = 'SCENARIO5')
      THEN
         --Scenario 5 - Convert Inflight to Modify and Cancel the earlier Modify.
         SELECT INV_ORDER_ID
           INTO vInventory_OrderId
           FROM DDDCA_MOD_ORDER
          WHERE DCOMP_ORDER_ID = p_i_inflight_order_id;

         UPDATE DDDCA_ORDER_DETAILS
            SET INFLIGHT_SCENARIO_ID = NULL
          WHERE ORDER_ID IN (p_i_inflight_order_id, vInventory_OrderId);

         UPDATE DDDCA_CUSTOMER
            SET ORDERTYPE = 'Modify'
          WHERE ORDER_ID IN (p_i_inflight_order_id, vInventory_OrderId);

         UPDATE DDDCA_SITE_SERVICE_INSTANCE
            SET INF_INST_ORDERTYPE = 'Modify', SER_INST_ORDERTYPE = 'Modify'
          WHERE SERVICEINSTANCEID IN (SELECT DDDCA_SITE_SERVICE_INSTANCE.SERVICEINSTANCEID
                                        FROM DDDCA_SITE,
                                             DDDCA_SITE_LOCATION,
                                             DDDCA_SITE_SERVICE_INSTANCE
                                       WHERE     DDDCA_SITE.ORDER_ID IN (p_i_inflight_order_id,
                                                                         vInventory_OrderId)
                                             AND DDDCA_SITE_LOCATION.SITE_ID =
                                                    DDDCA_SITE.SITE_ID
                                             AND DDDCA_SITE_SERVICE_INSTANCE.SITE_LOCATION_ID =
                                                    DDDCA_SITE_LOCATION.SITE_LOCATION_ID);

         SELECT COUNT (ORDEROWNER)
           INTO vCount
           FROM DDDCA_ORDER_DETAILS
          WHERE ORDER_ID = p_i_earlier_order_id;

         IF vCount = 0
         THEN
            UPDATE DDDCA_ORDER_DETAILS
               SET ORDERSTATUS = 'CANCELED ORDER',
                   CANCELEDBY = p_i_user_id,
                   CANCELREASON =
                      'Inflight for Modify order converted to Modify order, hence this earlier Modify order is cancelled.',
                   CANCELEDBYTEAM = p_i_team_id,
                   DELEGATEDTO = NULL,
                   DELEGATEDBY = NULL,
                   DELEGATEREASON = NULL,
                   DELEGATEDTOTEAMID = NULL,
                   DELEGATEDBYTEAMID = NULL
             WHERE ORDER_ID = p_i_earlier_order_id;

            vEmailId := NULL;
            vErrorDesc := 'OK-Previous order unassigned';
         ELSE
            --Submit the earlier Modify to Cancel queue.
            DDDCA_PK_USER_PROFILE.DDDCA_sp_SaveReason (
               'CANCEL',
               p_i_user_id,
               'Inflight for Modify order converted to Modify order, hence this earlier Modify order is cancelled.',
               p_i_earlier_order_id,
               p_i_team_category_id,
               vEmailId,
               vErrorDesc,
               p_i_team_id);
         END IF;

         p_o_email_id := vEmailId;
         p_o_error_desc := vErrorDesc;

         IF vErrorDesc = 'OK' OR vErrorDesc = 'OK-Previous order unassigned'
         THEN
            COMMIT;
         ELSE
            ROLLBACK;
         END IF;
      ELSE
         p_o_error_desc :=
               'The order '
            || p_i_inflight_order_id
            || ' cannot be converted to Provide or Modify order type.';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_error_desc := SQLERRM;
         ROLLBACK;
      WHEN VALUE_ERROR
      THEN
         p_o_error_desc := SQLERRM;
         ROLLBACK;
      WHEN DUP_VAL_ON_INDEX
      THEN
         p_o_error_desc := SQLERRM;
         ROLLBACK;
      WHEN OTHERS
      THEN
         p_o_error_desc := SQLERRM;
         ROLLBACK;
   END dca_convert_order;

   -----------------------------------------------------------------------------------

   PROCEDURE dca_get_earlier_order (p_i_inflight_order_id   IN     NUMBER,
                                    p_i_order_type          IN     VARCHAR2,
                                    p_o_earlier_order_id       OUT NUMBER,
                                    p_o_error_desc             OUT VARCHAR2)
   AS
   BEGIN
      IF UPPER (p_i_order_type) = 'ADD'
      THEN
         SELECT MAX (dc.ORDER_ID)
           INTO p_o_earlier_order_id
           FROM DDDCA_CUSTOMER dc, DDDCA_ORDER_DETAILS dod
          WHERE     dc.EXTERNALSYSTEMREFERENCE =
                       (SELECT DISTINCT EXTERNALSYSTEMREFERENCE
                          FROM DDDCA_CUSTOMER
                         WHERE ORDER_ID = p_i_inflight_order_id)
                AND dc.ORDERTYPE = p_i_order_type
                AND dc.ORDER_ID = dod.ORDER_ID
                AND dod.ORDERSTATUS <> 'CANCELED ORDER'
                AND dc.ORDER_ID NOT IN (p_i_inflight_order_id);

         p_o_error_desc := 'OK';
      ELSIF UPPER (p_i_order_type) = 'MODIFY'
      THEN
         SELECT MAX (dc.ORDER_ID)
           INTO p_o_earlier_order_id
           FROM DDDCA_CUSTOMER dc,
                DDDCA_ORDER_DETAILS dod,
                DDDCA_MOD_ORDER dmo
          WHERE     dc.EXTERNALSYSTEMREFERENCE =
                       (SELECT DISTINCT EXTERNALSYSTEMREFERENCE
                          FROM DDDCA_CUSTOMER
                         WHERE ORDER_ID = p_i_inflight_order_id)
                AND dc.ORDERTYPE = 'Modify'
                AND dc.ORDER_ID = dod.ORDER_ID
                AND dod.ORDERSTATUS <> 'CANCELED ORDER'
                AND dmo.DCOMP_ORDER_ID = dc.ORDER_ID
                AND dc.ORDER_ID NOT IN (p_i_inflight_order_id);

         p_o_error_desc := 'OK';
      ELSE
         p_o_error_desc := 'Wrong order type.';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_error_desc := SQLERRM;
      WHEN OTHERS
      THEN
         p_o_error_desc := SQLERRM;
   END dca_get_earlier_order;

   PROCEDURE GetInventoryOrders (pUserid      IN     NUMBER,
                                 pTeamId      IN     NUMBER,
                                 pORT_FLAG    IN     VARCHAR2,
                                 V_PG_START   IN     NUMBER,-- added the attribute for pagination by shivani
                                 v_pg_end     IN     NUMBER,-- added the attribute for pagination by shivani
                                 io_cursor       OUT t_cursor)
   AS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
         SELECT *            --added by shivani for pagination
           FROM (SELECT OID,
                        ORDER_REFERENCE,
                        CLIENT_ACCOUNT_ID,
                        CLIENT_NAME,
                        ORDER_RECEIVED_DATE,
                        status,
                        Int_Id,
                        Ordertype,
                        INFLIGHT_SCENARIO_ID,
                        Q_BATCH_ID,
                        ROW_NUMBER () OVER (ORDER BY OID DESC) rn,
                        COUNT (*) OVER () cnt
                   FROM (  SELECT DISTINCT
                                  dmo.dcomp_order_id OID,
                                  dd_dca_pk.concat_orderreference (
                                     dmo.inv_order_id,
                                     '/')
                                     ORDER_REFERENCE, --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                                  --drc.ORDER_REFERENCE,
                                  --drc.CLIENT_ACCOUNT_ID,
                                  dd_dca_pk.CONCAT_ACCOUNTID (dmo.inv_order_id,
                                                              '/')
                                     CLIENT_ACCOUNT_ID,
                                  drc.CLIENT_NAME,
                                  TO_CHAR (drc.ORDER_RECEIVED_DATE,
                                           'DD/MM/YYYY')
                                     ORDER_RECEIVED_DATE,
                                  MAX (
                                     DECODE (status,
                                             'MSG_GEN', 'Sent',
                                             'FAIL', 'Failed',
                                             'SUCCESS', 'Success'))
                                     status,
                                  MAX (q_interaction_id) Int_Id,
                                  dc.Ordertype,
                                  dod.INFLIGHT_SCENARIO_ID,
                                  dci.Q_BATCH_ID
                             FROM dca_cla_interaction dci,
                                  dddca_mod_order dmo,
                                  dddca_reuters_client drc,
                                  dddca_order_details dod,
                                  dddca_site ds,
                                  dddca_customer dc
                            WHERE     (   dod.orderowner = pUserid
                                       OR dod.orderowner IS NULL)
                                  AND dod.TeamId = pTeamId
                                  AND dod.ort_flag = pORT_FLAG
                                  AND dod.ORDER_ID = drc.ORDERID
                                  AND (   dod.INFLIGHT_SCENARIO_ID NOT IN ('SCENARIO1',
                                                                           'SCENARIO2',
                                                                           'SCENARIO4',
                                                                           'SCENARIO5')
                                       OR dod.INFLIGHT_SCENARIO_ID IS NULL)
                                  AND dmo.inv_order_id = dci.D_ORDER_ID
                                  AND dmo.inv_order_id = drc.ORDERID
                                  AND dod.Order_id = dc.order_id
                                  AND dci.Q_INTERACTION_ID =
                                         (SELECT MAX (q_interaction_id)
                                            FROM dca_cla_interaction dci1
                                           WHERE     (dci1.q_batch_id = 1)
                                                 AND dci1.d_ORDER_ID =
                                                        dmo.inv_order_id)
                                  AND ds.ORDER_ID = dmo.inv_order_id
                                  AND ds.SITE_ID =
                                         (SELECT MIN (site_id)
                                            FROM dddca_site
                                           WHERE (dddca_site.order_id =
                                                     dmo.inv_order_id))
                                  AND drc.SITE_ID = ds.site_id
                                  AND dod.ORDERSTATUS <> 'CANCELED ORDER' --Pramod Mathew ----Cancelled orders should not be shown ---
                         --group by dmo.dcomp_order_id,drc.ORDER_REFERENCE,drc.CLIENT_ACCOUNT_ID,drc.CLIENT_NAME,drc.ORDER_RECEIVED_DATE,dc.ordertype,dod.INFLIGHT_SCENARIO_ID,dci.Q_BATCH_ID
                         GROUP BY dmo.dcomp_order_id,
                                  dd_dca_pk.concat_orderreference (
                                     dmo.inv_order_id,
                                     '/'),
                                  dd_dca_pk.CONCAT_ACCOUNTID (
                                     dmo.inv_order_id,
                                     '/'),
                                  drc.CLIENT_NAME,
                                  drc.ORDER_RECEIVED_DATE,
                                  dc.ordertype,
                                  dod.INFLIGHT_SCENARIO_ID,
                                  dci.Q_BATCH_ID --Comment by Sujit Kumar Sahu on 26-Feb-2008 For fixing SIT defect 26061 - R891
                         UNION
                           SELECT DISTINCT
                                  dmo.dcomp_order_id OID,
                                  dd_dca_pk.concat_orderreference (
                                     dod.ORDER_ID,
                                     '/')
                                     ORDER_REFERENCE, --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                                  --drc.ORDER_REFERENCE,
                                  --drc.CLIENT_ACCOUNT_ID,
                                  dd_dca_pk.CONCAT_ACCOUNTID (dod.ORDER_ID,
                                                              '/')
                                     CLIENT_ACCOUNT_ID,
                                  drc.CLIENT_NAME,
                                  TO_CHAR (drc.ORDER_RECEIVED_DATE,
                                           'DD/MM/YYYY')
                                     ORDER_RECEIVED_DATE,
                                  MAX (
                                     DECODE (status,
                                             'MSG_GEN', 'Sent',
                                             'FAIL', 'Failed',
                                             'SUCCESS', 'Success'))
                                     status,
                                  MAX (q_interaction_id) Int_Id,
                                  dc.Ordertype,
                                  dod.INFLIGHT_SCENARIO_ID,
                                  dci.Q_BATCH_ID
                             FROM dca_cla_interaction dci,
                                  dddca_mod_order dmo,
                                  dddca_reuters_client drc,
                                  dddca_order_details dod,
                                  dddca_site ds,
                                  dddca_customer dc
                            WHERE     (   dod.orderowner = pUserid
                                       OR dod.orderowner IS NULL)
                                  AND dod.TeamId = pTeamId
                                  AND dod.ort_flag = pORT_FLAG
                                  AND dod.ORDER_ID = drc.ORDERID
                                  AND dmo.inv_order_id = dci.D_ORDER_ID
                                  AND dmo.inv_order_id = drc.ORDERID
                                  AND dod.Order_id = dc.order_id
                                  AND (   dod.INFLIGHT_SCENARIO_ID NOT IN ('SCENARIO1',
                                                                           'SCENARIO2',
                                                                           'SCENARIO4',
                                                                           'SCENARIO5')
                                       OR dod.INFLIGHT_SCENARIO_ID IS NULL)
                                  AND dci.Q_INTERACTION_ID =
                                         (SELECT MAX (q_interaction_id)
                                            FROM dca_cla_interaction dci1
                                           WHERE     dci1.q_batch_id = 2
                                                 AND dci1.d_ORDER_ID =
                                                        dmo.inv_order_id)
                                  AND ds.ORDER_ID = dmo.inv_order_id
                                  AND ds.SITE_ID =
                                         (SELECT MAX (site_id)
                                            FROM dddca_site
                                           WHERE dddca_site.order_id =
                                                    dmo.inv_order_id)
                                  AND drc.SITE_ID = ds.site_id
                                  AND dod.ORDERSTATUS <> 'CANCELED ORDER' --Pramod Mathew -- Cancelled orders should not be shown ---
                         --group by dmo.dcomp_order_id,drc.ORDER_REFERENCE,drc.CLIENT_ACCOUNT_ID,drc.CLIENT_NAME,drc.ORDER_RECEIVED_DATE,dc.ordertype,dod.INFLIGHT_SCENARIO_ID,dci.Q_BATCH_ID
                         GROUP BY dmo.dcomp_order_id,
                                  dd_dca_pk.concat_orderreference (
                                     dod.ORDER_ID,
                                     '/'),
                                  dd_dca_pk.CONCAT_ACCOUNTID (dod.ORDER_ID,
                                                              '/'),
                                  drc.CLIENT_NAME,
                                  drc.ORDER_RECEIVED_DATE,
                                  dc.ordertype,
                                  dod.INFLIGHT_SCENARIO_ID,
                                  dci.Q_BATCH_ID --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                         UNION
                         SELECT DISTINCT
                                dod.Order_id OID,
                                dd_dca_pk.concat_orderreference (
                                   dod.ORDER_ID,
                                   '/')
                                   ORDER_REFERENCE, --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                                --drc.ORDER_REFERENCE,
                                --drc.CLIENT_ACCOUNT_ID,
                                dd_dca_pk.CONCAT_ACCOUNTID (dod.ORDER_ID,
                                                            '/')
                                   CLIENT_ACCOUNT_ID,
                                drc.CLIENT_NAME,
                                TO_CHAR (drc.ORDER_RECEIVED_DATE,
                                         'DD/MM/YYYY')
                                   ORDER_RECEIVED_DATE,
                                'Failed',
                                1,
                                dc.Ordertype,
                                dod.INFLIGHT_SCENARIO_ID,
                                NULL
                           FROM dddca_reuters_client drc,
                                dddca_order_details dod,
                                dddca_customer dc,
                                dddca_mod_order dmo
                          WHERE     (   dod.orderowner = pUserid
                                     OR dod.orderowner IS NULL)
                                AND dod.TeamId = pTeamId
                                AND dod.ort_flag = pORT_FLAG
                                AND dod.Order_id = dc.order_id
                                AND dod.ORDER_ID = drc.ORDERID
                                AND dod.order_id = dmo.DCOMP_ORDER_ID(+)
                                AND dc.ORDERTYPE = 'Inflight'
                                AND dod.INFLIGHT_SCENARIO_ID IN ('SCENARIO1',
                                                                 'SCENARIO2',
                                                                 'SCENARIO4',
                                                                 'SCENARIO5')
                                AND dod.ORDER_ID NOT IN (SELECT dmo1.INV_ORDER_ID
                                                           FROM dddca_mod_order dmo1)
                                AND dod.ORDERSTATUS <> 'CANCELED ORDER' --Pramod Mathew ---Cancelled orders should not be shown ---
                         ORDER BY OID DESC))
          WHERE rn BETWEEN v_pg_start AND v_pg_end;

      --Comment by Sujit Kumar Sahu on 26-Feb-2008, below sql query for Tuning and For fixing SIT defect 26061 - R891
      /*                  Select  distinct dod.Order_id OID,
                       drc.ORDER_REFERENCE,
                       drc.CLIENT_ACCOUNT_ID,
                       drc.CLIENT_NAME,
                       TO_CHAR(drc.ORDER_RECEIVED_DATE,'DD/MM/YYYY') ORDER_RECEIVED_DATE,
                       'Failed',1,
                       dc.Ordertype,
                       dod.INFLIGHT_SCENARIO_ID,
                       null
                       From
                       dddca_reuters_client drc,
                       dddca_order_details dod,
                       dddca_site ds,
                       dddca_customer dc,
                       dddca_mod_order dmo
                       where(dod.orderowner = pUserid Or dod.orderowner Is null)
                       and dod.TeamId =pTeamId
                       and dod.ort_flag=pORT_FLAG
                       and dod.Order_id=dc.order_id
                       and dod.ORDER_ID=drc.ORDERID
                       and dod.order_id=dmo.DCOMP_ORDER_ID(+)
                       and dc.ORDERTYPE='Inflight'
                       and dod.INFLIGHT_SCENARIO_ID IN('SCENARIO1','SCENARIO2','SCENARIO4','SCENARIO5')
                       and dod.ORDER_ID not in(Select dmo1.INV_ORDER_ID From dddca_mod_order dmo1)
                       and dod.ORDERSTATUS <> 'CANCELED ORDER'--Pramod Mathew ---Cancelled orders should not be shown ---
                       order by OID desc ;*/
      io_cursor := v_cursor;
   END GetInventoryOrders;

   PROCEDURE GetFailedInventoryOrders (pUserid     IN     NUMBER,
                                       pTeamId     IN     NUMBER,
                                       pORT_FLAG   IN     VARCHAR2,
                                       io_cursor      OUT t_cursor)
   AS
   --v_cursor t_cursor;
   BEGIN
      OPEN io_cursor FOR
         SELECT A.oid AS ORDER_ID,
                A.ORDER_REFERENCE,
                A.CLIENT_ACCOUNT_ID,
                A.CLIENT_NAME AS "Client Name",
                A.ORDER_RECEIVED_DATE AS RECEIVED_DATE,
                '' AS MENU_FLAG,
                '' AS MENU_NAME
           FROM (  SELECT DISTINCT
                          dmo.dcomp_order_id oid,
                          dd_dca_pk.concat_orderreference (dmo.inv_order_id,
                                                           '/')
                             ORDER_REFERENCE, --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                          --drc.ORDER_REFERENCE,
                          --drc.CLIENT_ACCOUNT_ID,
                          dd_dca_pk.CONCAT_ACCOUNTID (dmo.inv_order_id, '/')
                             CLIENT_ACCOUNT_ID,
                          drc.CLIENT_NAME,
                          TO_CHAR (drc.ORDER_RECEIVED_DATE, 'DD/MM/YYYY')
                             ORDER_RECEIVED_DATE,
                          MAX (
                             DECODE (status,
                                     'MSG_GEN', 'Sent',
                                     'FAIL', 'Failed',
                                     'SUCCESS', 'Success'))
                             status,
                          MAX (q_interaction_id) Int_Id,
                          dc.Ordertype,
                          dod.INFLIGHT_SCENARIO_ID,
                          dci.Q_BATCH_ID
                     FROM dca_cla_interaction dci,
                          dddca_mod_order dmo,
                          dddca_reuters_client drc,
                          dddca_order_details dod,
                          dddca_site ds,
                          dddca_customer dc
                    WHERE     (   dod.orderowner = pUserid
                               OR dod.orderowner IS NULL)
                          AND dod.TeamId = pTeamId
                          AND dod.ort_flag = pORT_FLAG
                          AND dod.ORDER_ID = drc.ORDERID
                          AND (   dod.INFLIGHT_SCENARIO_ID NOT IN ('SCENARIO1',
                                                                   'SCENARIO2',
                                                                   'SCENARIO4',
                                                                   'SCENARIO5')
                               OR dod.INFLIGHT_SCENARIO_ID IS NULL)
                          AND dmo.inv_order_id = dci.D_ORDER_ID
                          AND dmo.inv_order_id = drc.ORDERID
                          AND dod.Order_id = dc.order_id
                          AND dci.Q_INTERACTION_ID =
                                 (SELECT MAX (q_interaction_id)
                                    FROM dca_cla_interaction dci1
                                   WHERE     (dci1.q_batch_id = 1)
                                         AND dci1.d_ORDER_ID = dmo.inv_order_id)
                          AND ds.ORDER_ID = dmo.inv_order_id
                          AND ds.SITE_ID =
                                 (SELECT MIN (site_id)
                                    FROM dddca_site
                                   WHERE (dddca_site.order_id =
                                             dmo.inv_order_id))
                          AND drc.SITE_ID = ds.site_id
                          AND dod.ORDERSTATUS <> 'CANCELED ORDER'
                          AND status = 'FAIL' --Pramod Mathew ----Cancelled orders should not be shown ---
                 --group by dmo.dcomp_order_id,drc.ORDER_REFERENCE,drc.CLIENT_ACCOUNT_ID,drc.CLIENT_NAME,drc.ORDER_RECEIVED_DATE,dc.ordertype,dod.INFLIGHT_SCENARIO_ID,dci.Q_BATCH_ID
                 GROUP BY dmo.dcomp_order_id,
                          dd_dca_pk.concat_orderreference (dmo.inv_order_id,
                                                           '/'),
                          dd_dca_pk.CONCAT_ACCOUNTID (dmo.inv_order_id, '/'),
                          drc.CLIENT_NAME,
                          drc.ORDER_RECEIVED_DATE,
                          dc.ordertype,
                          dod.INFLIGHT_SCENARIO_ID,
                          dci.Q_BATCH_ID --Comment by Sujit Kumar Sahu on 26-Feb-2008 For fixing SIT defect 26061 - R891
                 UNION
                   SELECT DISTINCT
                          dmo.dcomp_order_id OID,
                          dd_dca_pk.concat_orderreference (dod.ORDER_ID, '/')
                             ORDER_REFERENCE, --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                          --drc.ORDER_REFERENCE,
                          --drc.CLIENT_ACCOUNT_ID,
                          dd_dca_pk.CONCAT_ACCOUNTID (dod.ORDER_ID, '/')
                             CLIENT_ACCOUNT_ID,
                          drc.CLIENT_NAME,
                          TO_CHAR (drc.ORDER_RECEIVED_DATE, 'DD/MM/YYYY')
                             ORDER_RECEIVED_DATE,
                          MAX (
                             DECODE (status,
                                     'MSG_GEN', 'Sent',
                                     'FAIL', 'Failed',
                                     'SUCCESS', 'Success'))
                             status,
                          MAX (q_interaction_id) Int_Id,
                          dc.Ordertype,
                          dod.INFLIGHT_SCENARIO_ID,
                          dci.Q_BATCH_ID
                     FROM dca_cla_interaction dci,
                          dddca_mod_order dmo,
                          dddca_reuters_client drc,
                          dddca_order_details dod,
                          dddca_site ds,
                          dddca_customer dc
                    WHERE     (   dod.orderowner = pUserid
                               OR dod.orderowner IS NULL)
                          AND dod.TeamId = pTeamId
                          AND dod.ort_flag = pORT_FLAG
                          AND dod.ORDER_ID = drc.ORDERID
                          AND dmo.inv_order_id = dci.D_ORDER_ID
                          AND dmo.inv_order_id = drc.ORDERID
                          AND dod.Order_id = dc.order_id
                          AND (   dod.INFLIGHT_SCENARIO_ID NOT IN ('SCENARIO1',
                                                                   'SCENARIO2',
                                                                   'SCENARIO4',
                                                                   'SCENARIO5')
                               OR dod.INFLIGHT_SCENARIO_ID IS NULL)
                          AND dci.Q_INTERACTION_ID =
                                 (SELECT MAX (q_interaction_id)
                                    FROM dca_cla_interaction dci1
                                   WHERE     dci1.q_batch_id = 2
                                         AND dci1.d_ORDER_ID = dmo.inv_order_id)
                          AND ds.ORDER_ID = dmo.inv_order_id
                          AND ds.SITE_ID =
                                 (SELECT MAX (site_id)
                                    FROM dddca_site
                                   WHERE dddca_site.order_id = dmo.inv_order_id)
                          AND drc.SITE_ID = ds.site_id
                          AND dod.ORDERSTATUS <> 'CANCELED ORDER'
                          AND status = 'FAIL' --Pramod Mathew -- Cancelled orders should not be shown ---
                 --group by dmo.dcomp_order_id,drc.ORDER_REFERENCE,drc.CLIENT_ACCOUNT_ID,drc.CLIENT_NAME,drc.ORDER_RECEIVED_DATE,dc.ordertype,dod.INFLIGHT_SCENARIO_ID,dci.Q_BATCH_ID
                 GROUP BY dmo.dcomp_order_id,
                          dd_dca_pk.concat_orderreference (dod.ORDER_ID, '/'),
                          dd_dca_pk.CONCAT_ACCOUNTID (dod.ORDER_ID, '/'),
                          drc.CLIENT_NAME,
                          drc.ORDER_RECEIVED_DATE,
                          dc.ordertype,
                          dod.INFLIGHT_SCENARIO_ID,
                          dci.Q_BATCH_ID --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                 UNION
                 SELECT DISTINCT
                        dod.Order_id OID,
                        dd_dca_pk.concat_orderreference (dod.ORDER_ID, '/')
                           ORDER_REFERENCE, --Comment by Sujit Kumar Sahu on 26-Feb-2008, For fixing SIT defect 26061 - R891
                        --drc.ORDER_REFERENCE,
                        --drc.CLIENT_ACCOUNT_ID,
                        dd_dca_pk.CONCAT_ACCOUNTID (dod.ORDER_ID, '/')
                           CLIENT_ACCOUNT_ID,
                        drc.CLIENT_NAME,
                        TO_CHAR (drc.ORDER_RECEIVED_DATE, 'DD/MM/YYYY')
                           ORDER_RECEIVED_DATE,
                        'Failed',
                        1,
                        dc.Ordertype,
                        dod.INFLIGHT_SCENARIO_ID,
                        NULL
                   FROM dddca_reuters_client drc,
                        dddca_order_details dod,
                        dddca_customer dc,
                        dddca_mod_order dmo
                  WHERE     (   dod.orderowner = pUserid
                             OR dod.orderowner IS NULL)
                        AND dod.TeamId = pTeamId
                        AND dod.ort_flag = pORT_FLAG
                        AND dod.Order_id = dc.order_id
                        AND dod.ORDER_ID = drc.ORDERID
                        AND dod.order_id = dmo.DCOMP_ORDER_ID(+)
                        AND dc.ORDERTYPE = 'Inflight'
                        AND dod.INFLIGHT_SCENARIO_ID IN ('SCENARIO1',
                                                         'SCENARIO2',
                                                         'SCENARIO4',
                                                         'SCENARIO5')
                        AND dod.ORDER_ID NOT IN (SELECT dmo1.INV_ORDER_ID
                                                   FROM dddca_mod_order dmo1)
                        AND dod.ORDERSTATUS <> 'CANCELED ORDER' --Pramod Mathew ---Cancelled orders should not be shown ---
                 ORDER BY Oid DESC) A;
   -- io_cursor := v_cursor;
   END GetFailedInventoryOrders;

   --Comment BEGIN by Sujit Kumar Sahu on 05-Jun-2009 for fixing Live Defect Id BF381987, BF377926 - R894
   PROCEDURE GET_RESILIENCE_ORDERTYPE (vOrderId       IN     NUMBER,
                                       vIResilience      OUT VARCHAR2,
                                       vIOrderType       OUT VARCHAR2,
                                       vErrorDesc        OUT VARCHAR2)
   IS
      v_IResilience   DDDCA_CUSTOMER.ORDERTYPE%TYPE := NULL;
      v_IOrderType    DDDCA_CUSTOMER.ORDERTYPE%TYPE := NULL;
   BEGIN
      BEGIN
         SELECT DISTINCT RESILIENCE
           INTO v_IResilience
           FROM DDDCA_SITE
          WHERE ORDER_ID = vOrderId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_IResilience := NULL;
         WHEN OTHERS
         THEN
            v_IResilience := NULL;
      END;

      BEGIN
         SELECT DISTINCT ORDERTYPE
           INTO v_IOrderType
           FROM DDDCA_CUSTOMER
          WHERE ORDER_ID = vOrderId AND UPPER (CUSTOMERTYPE) = 'S';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_IOrderType := NULL;
         WHEN OTHERS
         THEN
            v_IOrderType := NULL;
      END;

      IF v_IResilience IS NOT NULL
      THEN
         vIResilience := v_IResilience;
      ELSE
         vIResilience := NULL;
      END IF;

      IF v_IOrderType IS NOT NULL
      THEN
         vIOrderType := v_IOrderType;
      ELSE
         vIOrderType := NULL;
      END IF;

      vErrorDesc := 'OK';
      RETURN;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         vErrorDesc := 'NOT OK';
         vIResilience := NULL;
         vIOrderType := NULL;
      WHEN OTHERS
      THEN
         vErrorDesc := 'NOT OK';
         vIResilience := NULL;
         vIOrderType := NULL;
   END GET_RESILIENCE_ORDERTYPE;

   --Comment END by Sujit Kumar Sahu on 05-Jun-2009 for fixing Live Defect Id BF381987, BF377926 - R894

   --Comment by Sujit Kumar Sahu on 28-Jan-2010 for code merge from R903
   --Comment BEGIN by Sujit Kumar Sahu on 27-Jan-2010 for CR JL2.3 - R903
   -----------LEASED LINE - LONGLINE ISSUE --------------------------------
   -----------determine to go with Leased line or Longline accesstype
   ------- ALways call this procedure if current POP is LONGLINE as per Databuild
   PROCEDURE IS_LEASED_LONGLINE (p_Amend_order_id           IN     NUMBER,
                                 p_INV_order_id             IN     NUMBER,
                                 p_new_service_instanceid   IN     NUMBER,
                                 p_old_service_instanceid   IN     NUMBER,
                                 p_ReturnValue                 OUT VARCHAR2)
   IS
      v_gpop_old          DDDCA_SITE_SERVICE_INSTANCE.GPOP%TYPE;
      v_gpop_new          DDDCA_SITE_SERVICE_INSTANCE.GPOP%TYPE;
      v_FinalAccessType   VARCHAR2 (100);
      v_CountAccess       NUMBER (10) := 0;
      v_CountLongline     NUMBER (10) := 0;
   BEGIN
      v_FinalAccessType := NULL;
      v_FinalAccessType := 'Long Line';


      BEGIN
         SELECT DSSI.GPOP
           INTO v_gpop_old
           FROM DDDCA_SITE_SERVICE_INSTANCE DSSI
          WHERE DSSI.SERVICEINSTANCEID = p_old_service_instanceid;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_gpop_old := NULL;
         WHEN OTHERS
         THEN
            v_gpop_old := NULL;
      END;

      BEGIN
         SELECT DSSI.GPOP
           INTO v_gpop_new
           FROM DDDCA_SITE_SERVICE_INSTANCE DSSI
          WHERE DSSI.SERVICEINSTANCEID = p_new_service_instanceid;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_gpop_new := NULL;
         WHEN OTHERS
         THEN
            v_gpop_new := NULL;
      END;

      v_CountAccess := 0;
      v_CountLongline := 0;

      SELECT COUNT (DCSO.Q_SERVICE_ID)
        INTO v_CountAccess
        FROM DCA_CLA_SERVICE_ORDER DCSO, DCA_CLA_SERVICE_ELEMENT DCSE
       WHERE     DCSO.SERVICEINSTANCEID = p_old_service_instanceid
             AND DCSO.D_ORDER_ID = p_INV_order_id
             AND DCSO.Q_SERVICE_ID = DCSE.Q_SERVICE_ID
             AND NVL (DCSE.PART_NUMBER, ' ') = '60000290';

      ---------------if old has access TE, then its child should have the same TE
      SELECT COUNT (DCSO.Q_SERVICE_ID)
        INTO v_CountLongline
        FROM DCA_CLA_SERVICE_ORDER DCSO, DCA_CLA_SERVICE_ELEMENT DCSE
       WHERE     DCSO.SERVICEINSTANCEID = p_old_service_instanceid
             AND DCSO.D_ORDER_ID = p_INV_order_id
             AND DCSO.Q_SERVICE_ID = DCSE.Q_SERVICE_ID
             AND NVL (DCSE.PART_NUMBER, ' ') = '50032849'; -------Longline header element

      IF (NVL (v_gpop_old, ' ') = NVL (v_gpop_new, ' '))
      THEN
         IF (v_CountAccess > 0)
         THEN
            v_FinalAccessType := 'Leased Line';
         ELSIF (v_CountLongline > 0)
         THEN
            v_FinalAccessType := 'Long Line';
         END IF;                       ----END OF  IF (v_CountAccess =0 ) THEN
      ELSE
         --------In case of GPOP has been changed-------
         v_FinalAccessType := 'Long Line'; ---as it is calling from Longline as YES-- new gpop is longline
      END IF;   ---END OF IF (NVL(v_gpop_old,' ') = NVL(v_gpop_new,' ') ) THEN

      p_ReturnValue := v_FinalAccessType;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_ReturnValue := 'Long Line';
   END IS_LEASED_LONGLINE;

   --Comment END by Sujit Kumar Sahu on 27-Jan-2010 for CR JL2.3 - R903

   --Comment BEGIN by Sujit Kumar Sahu on 24-Feb-2010 for Logical and RCD Amends related to CR260210 and CR260211 - R903.x
   PROCEDURE GET_INV_DIFF (p_i_orderid             IN     NUMBER,
                           p_i_seviceinstance      IN     VARCHAR2,
                           p_i_serviceinstanceid   IN     NUMBER,
                           p_o_error_description      OUT VARCHAR2,
                           p_i_CPESeqID            IN     NUMBER)
   IS
      v_inv_cnt                 NUMBER (10);
      v_dcomp_cnt               NUMBER (10);

      v_inv_orderid             dddca_mod_order.inv_order_id%TYPE;
      v_inv_serviceinstanceid   dddca_site_service_instance.serviceinstanceid%TYPE;

      v_inv_cpe                 dddca_service_instance_cpe.baserouter%TYPE;
      v_dcomp_cpe               dddca_service_instance_cpe.baserouter%TYPE;
      v_inv_interfacetype       dddca_serv_inst_accesstype.interfacetype%TYPE;
      v_dcomp_interfacetype     dddca_serv_inst_accesstype.interfacetype%TYPE;
   BEGIN
      BEGIN
         SELECT dddca_mod_order.inv_order_id
           INTO v_inv_orderid
           FROM dddca_mod_order
          WHERE dddca_mod_order.dcomp_order_id = p_i_orderid;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
         WHEN OTHERS
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
      END;

      BEGIN
         IF v_inv_orderid IS NOT NULL AND v_inv_orderid > 0
         THEN
            SELECT dddca_site_service_instance.serviceinstanceid
              INTO v_inv_serviceinstanceid
              FROM dddca_site,
                   dddca_site_location,
                   dddca_site_service_instance
             WHERE     dddca_site.order_id = v_inv_orderid
                   AND dddca_site.site_id = dddca_site_location.site_id
                   AND dddca_site_location.site_location_id =
                          dddca_site_service_instance.site_location_id
                   AND UPPER (
                          REPLACE (
                             dddca_site_service_instance.serviceinstance,
                             'Ultra-',
                             '')) =
                          UPPER (REPLACE (p_i_seviceinstance, 'Ultra-', '')); -- Rajesh- Defect 5097- Service instance change from Primary/Secondary to Ultra-Primary/Ultra-Secondary
         ELSE
            p_o_error_description := 'NOT OK';
            RETURN;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
         WHEN OTHERS
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
      END;

      BEGIN
         SELECT dddca_serv_inst_accesstype.interfacetype,
                dddca_service_instance_cpe.baserouter
           INTO v_inv_interfacetype, v_inv_cpe
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_serv_inst_accesstype,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = v_inv_orderid
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       v_inv_serviceinstanceid
                AND dddca_serv_inst_accesstype.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_serv_inst_accesstype.serviceinstanceid
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND dddca_service_instance_cpe.cpeseqid = p_i_CPESeqID;

         DBMS_OUTPUT.put_line (v_inv_interfacetype || v_inv_cpe);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_inv_interfacetype := NULL;
            v_inv_cpe := NULL;
         WHEN OTHERS
         THEN
            v_inv_interfacetype := NULL;
            v_inv_cpe := NULL;
      END;

      BEGIN
         SELECT dddca_serv_inst_accesstype.interfacetype,
                dddca_service_instance_cpe.baserouter
           INTO v_dcomp_interfacetype, v_dcomp_cpe
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_serv_inst_accesstype,
                dddca_service_instance_cpe
          WHERE     dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       p_i_serviceinstanceid
                AND dddca_serv_inst_accesstype.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_serv_inst_accesstype.serviceinstanceid
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND dddca_service_instance_cpe.cpeseqid = p_i_CPESeqID;

         DBMS_OUTPUT.put_line (v_dcomp_interfacetype || v_dcomp_cpe);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_dcomp_interfacetype := NULL;
            v_dcomp_cpe := NULL;
         WHEN OTHERS
         THEN
            v_dcomp_interfacetype := NULL;
            v_dcomp_cpe := NULL;
      END;

      IF     v_dcomp_cpe = v_inv_cpe
         AND v_inv_interfacetype = v_dcomp_interfacetype
      THEN
         DBMS_OUTPUT.put_line (
               v_dcomp_cpe
            || v_inv_cpe
            || v_inv_interfacetype
            || v_dcomp_interfacetype);
         p_o_error_description := 'OK';
      ELSE
         p_o_error_description := 'NOT OK';
      END IF;
   END GET_INV_DIFF;

   PROCEDURE GET_INV_SERVICEINSTANCEID (
      p_i_orderid             IN     NUMBER,
      p_i_seviceinstance      IN     VARCHAR2,
      p_o_serviceinstanceid      OUT NUMBER,
      p_o_error_description      OUT VARCHAR2)
   IS
      v_inv_orderid             dddca_mod_order.inv_order_id%TYPE;
      v_inv_serviceinstanceid   dddca_site_service_instance.serviceinstanceid%TYPE;
   BEGIN
      BEGIN
         SELECT dddca_mod_order.inv_order_id
           INTO v_inv_orderid
           FROM dddca_mod_order
          WHERE dddca_mod_order.dcomp_order_id = p_i_orderid;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
         WHEN OTHERS
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
      END;

      BEGIN
         IF v_inv_orderid IS NOT NULL AND v_inv_orderid > 0
         THEN
            SELECT dddca_site_service_instance.serviceinstanceid
              INTO v_inv_serviceinstanceid
              FROM dddca_site,
                   dddca_site_location,
                   dddca_site_service_instance
             WHERE     dddca_site.order_id = v_inv_orderid
                   AND dddca_site.site_id = dddca_site_location.site_id
                   AND dddca_site_location.site_location_id =
                          dddca_site_service_instance.site_location_id
                   AND UPPER (
                          REPLACE (
                             dddca_site_service_instance.serviceinstance,
                             'Ultra-',
                             '')) =
                          UPPER (REPLACE (p_i_seviceinstance, 'Ultra-', '')); -- Rajesh- Defect 5097- Service instance change from Primary/Secondary to Ultra-Primary/Ultra-Secondary
         ELSE
            p_o_error_description := 'NOT OK';
            RETURN;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            p_o_error_description := 'NOT OK';
            v_inv_serviceinstanceid := NULL;
         WHEN OTHERS
         THEN
            p_o_error_description := 'NOT OK';
            v_inv_serviceinstanceid := NULL;
      END;

      IF v_inv_serviceinstanceid IS NOT NULL
      THEN
         p_o_serviceinstanceid := v_inv_serviceinstanceid;
         p_o_error_description := 'OK';
      ELSE
         p_o_serviceinstanceid := v_inv_serviceinstanceid;
         p_o_error_description := 'NOT OK';
      END IF;
   END GET_INV_SERVICEINSTANCEID;

   PROCEDURE IS_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                            p_inv_serviceinstanceid   IN     NUMBER,
                            p_o_errordescription         OUT VARCHAR2,
                            p_i_SeqId                 IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
      n_cardid       dddca_cpe_card_configuration.card_id%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_cpe_card_configuration
          WHERE     dddca_cpe_card_configuration.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         BEGIN
            FOR c_card
               IN (SELECT *
                     FROM dddca_cpe_card_configuration
                    WHERE     serviceinstanceid = p_inv_serviceinstanceid
                          AND CPESEQID = p_i_SeqId)
            LOOP
               SELECT seq_cpecard.NEXTVAL INTO n_cardid FROM DUAL;

               c_card.serviceinstanceid := p_i_serviceinstanceid;
               c_card.card_id := n_cardid;

               INSERT INTO dddca_cpe_card_configuration
                    VALUES c_card;
            END LOOP cpe_card_configuration;

            COMMIT;
            p_o_errordescription := 'OK';
         EXCEPTION
            WHEN VALUE_ERROR
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
            WHEN OTHERS
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
         END;
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
   END IS_CARDEXISTS;

   PROCEDURE IS_CPE_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                                p_inv_serviceinstanceid   IN     NUMBER,
                                p_o_errordescription         OUT VARCHAR2,
                                p_i_SeqId                 IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
      n_cardid       dddca_extended_portfolio.element_id%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_extended_portfolio
          WHERE     dddca_extended_portfolio.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         BEGIN
            FOR c_card
               IN (SELECT *
                     FROM dddca_extended_portfolio
                    WHERE     serviceinstanceid = p_inv_serviceinstanceid
                          AND CPESEQID = p_i_SeqId)
            LOOP
               SELECT seq_cpecard.NEXTVAL INTO n_cardid FROM DUAL;

               c_card.serviceinstanceid := p_i_serviceinstanceid;
               c_card.element_id := n_cardid;

               INSERT INTO dddca_extended_portfolio
                    VALUES c_card;
            END LOOP cpe_card_configuration;

            COMMIT;
            p_o_errordescription := 'OK';
         EXCEPTION
            WHEN VALUE_ERROR
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
            WHEN OTHERS
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
         END;
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
   END IS_CPE_CARDEXISTS;

   ---ADDED BY KANAKSHREE R16 INCREMENTAL HARDWARE
   PROCEDURE IS_LAN_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                                p_inv_serviceinstanceid   IN     NUMBER,
                                p_o_errordescription         OUT VARCHAR2,
                                p_i_SeqId                 IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
      n_cardid       DDDCA_EXTEND_PORTFOLIO_SWITCH.element_id%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM DDDCA_EXTEND_PORTFOLIO_SWITCH
          WHERE     DDDCA_EXTEND_PORTFOLIO_SWITCH.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         BEGIN
            FOR c_card
               IN (SELECT *
                     FROM DDDCA_EXTEND_PORTFOLIO_SWITCH
                    WHERE     serviceinstanceid = p_inv_serviceinstanceid
                          AND CPESEQID = p_i_SeqId)
            LOOP
               SELECT seq_cpecard.NEXTVAL INTO n_cardid FROM DUAL;

               c_card.serviceinstanceid := p_i_serviceinstanceid;
               c_card.element_id := n_cardid;

               INSERT INTO DDDCA_EXTEND_PORTFOLIO_SWITCH
                    VALUES c_card;
            END LOOP cpe_card_configuration;

            COMMIT;
            p_o_errordescription := 'OK';
         EXCEPTION
            WHEN VALUE_ERROR
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
            WHEN OTHERS
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
         END;
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
   END IS_LAN_CARDEXISTS;

   ---COMMENT ENDED BY KANAKSHREE FOR INCREMENTAL HARDWARE
   ----Comment begin by Kanakshree Jaiswal for GCSE 62414
   PROCEDURE GetAuthCPEData (ServiInst        NUMBER,
                             cpeseqid         NUMBER,
                             parentpage       VARCHAR2,
                             io_cursor    OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
         SELECT AUTHORISATION_CODE, APPROVER, APPROVAL_DATE
           FROM dddca_service_instance_cpe
          WHERE SERVICEINSTANCEID = ServiInst AND CPESEQID = cpeseqid;

      io_cursor := v_cursor;
   END GetAuthCPEData;

   PROCEDURE GetAuthLANData (ServiInst        NUMBER,
                             cpeseqid         NUMBER,
                             parentpage       VARCHAR2,
                             io_cursor    OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
         SELECT AUTHORISATION_CODE, APPROVER, APPROVAL_DATE
           FROM dddca_lan_switch_cpe
          WHERE SERVICEINSTANCEID = ServiInst AND CPESEQID = cpeseqid;

      io_cursor := v_cursor;
   END GetAuthLANData;

   ----Comment ended by Kanakshree Jaiswal for GCSE 62414


   PROCEDURE GET_CARDDESCRIPTION (p_i_serviceinstanceid   IN     NUMBER,
                                  p_i_cardposition        IN     VARCHAR2,
                                  p_o_carddescription        OUT VARCHAR2,
                                  p_o_errordescription       OUT VARCHAR2,
                                  p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
      v_card_desc    DDDCA_CPE_CARD_CONFIGURATION.card_desc%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_cpe_card_configuration
          WHERE     dddca_cpe_card_configuration.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId
                AND UPPER (dddca_cpe_card_configuration.card_position) =
                       UPPER (p_i_cardposition);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := 0;
         WHEN OTHERS
         THEN
            v_count_rows := 0;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         BEGIN
            SELECT dddca_cpe_card_configuration.card_desc
              INTO v_card_desc
              FROM dddca_cpe_card_configuration
             WHERE     dddca_cpe_card_configuration.serviceinstanceid =
                          p_i_serviceinstanceid
                   AND CPESEQID = p_i_SeqId
                   AND dddca_cpe_card_configuration.card_position =
                          p_i_cardposition;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               v_card_desc := NULL;
            WHEN OTHERS
            THEN
               v_card_desc := NULL;
         END;
      ELSE
         v_card_desc := NULL;
      END IF;

      IF v_card_desc IS NOT NULL
      THEN
         p_o_carddescription := v_card_desc;
         p_o_errordescription := 'OK';
      ELSE
         p_o_carddescription := v_card_desc;
         p_o_errordescription := 'NOT OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END GET_CARDDESCRIPTION;

   PROCEDURE IS_CARDNOTEXISTS (p_i_serviceinstanceid   IN     NUMBER,
                               p_o_errordescription       OUT VARCHAR2,
                               p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_cpe_card_configuration
          WHERE     dddca_cpe_card_configuration.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         p_o_errordescription := 'NOT OK';
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END IS_CARDNOTEXISTS;

   --Comment END by Sujit Kumar Sahu on 24-Feb-2010 for Logical and RCD Amends related to CR260210 and CR260211 - R903.x

   --Comment BEGIN by Sujit Kumar Sahu on 03-May-2010 for fixing SIT Defect Id 70200 - R011
   PROCEDURE check_get_hecpe (v_cpe_name            IN     VARCHAR2,
                              v_cpe_series             OUT VARCHAR2,
                              v_error_description      OUT VARCHAR2)
   IS
      v_cnt   NUMBER (10);
      v_cpe   VARCHAR2 (20);
   BEGIN
      BEGIN
         SELECT COUNT (dddca_highend_cpe.cpe_name)
           INTO v_cnt
           FROM dddca_highend_cpe
          WHERE dddca_highend_cpe.cpe_name = v_cpe_name;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_cnt := 0;
         WHEN OTHERS
         THEN
            v_cnt := 0;
      END;

      IF v_cnt > 0
      THEN
         BEGIN
            SELECT dddca_highend_cpe.cpe_series
              INTO v_cpe
              FROM dddca_highend_cpe
             WHERE dddca_highend_cpe.cpe_name = v_cpe_name;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               v_error_description := 'NOT OK';
               v_cpe_series := ' ';
               RETURN;
            WHEN OTHERS
            THEN
               v_error_description := 'NOT OK';
               v_cpe_series := ' ';
               RETURN;
         END;
      ELSE
         v_error_description := 'NOT OK';
         v_cpe_series := ' ';
         RETURN;
      END IF;

      IF v_cpe IS NOT NULL
      THEN
         v_cpe_series := v_cpe;
         v_error_description := 'OK';
      ELSE
         v_error_description := 'NOT OK';
         v_cpe_series := ' ';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         v_error_description := 'NOT OK';
      WHEN OTHERS
      THEN
         v_error_description := 'NOT OK';
   END check_get_hecpe;

   PROCEDURE check_hecpe_count (v_order_id            IN     NUMBER,
                                v_primary_count          OUT NUMBER,
                                v_secondary_count        OUT NUMBER,
                                v_error_description      OUT VARCHAR2)
   IS
      v_cnt_primary     NUMBER (10);
      v_cnt_secondary   NUMBER (10);
   BEGIN
      BEGIN
         SELECT COUNT (dddca_service_instance_cpe.serviceinstanceid)
           INTO v_cnt_primary
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = v_order_id
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       'PRIMARY'
                AND UPPER (dddca_service_instance_cpe.baserouter) IN (SELECT UPPER (
                                                                                dddca_highend_cpe.cpe_name)
                                                                        FROM dddca_highend_cpe);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_cnt_primary := 0;
         WHEN OTHERS
         THEN
            v_cnt_primary := 0;
      END;

      BEGIN
         SELECT COUNT (dddca_service_instance_cpe.serviceinstanceid)
           INTO v_cnt_secondary
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = v_order_id
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       'SECONDARY'
                AND UPPER (dddca_service_instance_cpe.baserouter) IN (SELECT UPPER (
                                                                                dddca_highend_cpe.cpe_name)
                                                                        FROM dddca_highend_cpe);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_cnt_secondary := 0;
         WHEN OTHERS
         THEN
            v_cnt_secondary := 0;
      END;

      IF v_cnt_primary IS NOT NULL
      THEN
         IF v_cnt_primary > 0
         THEN
            v_primary_count := v_cnt_primary;
         ELSE
            v_primary_count := 0;
         END IF;
      END IF;

      IF v_cnt_secondary IS NOT NULL
      THEN
         IF v_cnt_secondary > 0
         THEN
            v_secondary_count := v_cnt_secondary;
         ELSE
            v_secondary_count := 0;
         END IF;
      END IF;

      v_error_description := 'OK';
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         v_cnt_primary := 0;
         v_cnt_secondary := 0;
         v_error_description := 'NOT OK';
      WHEN OTHERS
      THEN
         v_cnt_primary := 0;
         v_cnt_secondary := 0;
         v_error_description := 'NOT OK';
   END check_hecpe_count;

   PROCEDURE get_sec_cpe (v_order_id            IN     NUMBER,
                          v_sec_cpe                OUT VARCHAR2,
                          v_error_description      OUT VARCHAR2)
   IS
      v_cnt_secondary   NUMBER (10);
      v_cpe_secondary   VARCHAR2 (500);
   BEGIN
      BEGIN
         SELECT COUNT (dddca_service_instance_cpe.serviceinstanceid)
           INTO v_cnt_secondary
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = v_order_id
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       'SECONDARY'
                AND UPPER (dddca_service_instance_cpe.baserouter) IN (SELECT UPPER (
                                                                                dddca_highend_cpe.cpe_name)
                                                                        FROM dddca_highend_cpe);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_cnt_secondary := 0;
         WHEN OTHERS
         THEN
            v_cnt_secondary := 0;
      END;

      BEGIN
         SELECT dddca_service_instance_cpe.baserouter
           INTO v_cpe_secondary
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = v_order_id
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       'SECONDARY';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_cpe_secondary := NULL;
         WHEN OTHERS
         THEN
            v_cpe_secondary := NULL;
      END;


      IF v_cpe_secondary IS NOT NULL
      THEN
         v_sec_cpe := v_cpe_secondary;
         v_error_description := 'OK';
      ELSE
         v_sec_cpe := NULL;
         v_error_description := 'NOT OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         v_sec_cpe := NULL;
         v_error_description := 'NOT OK';
      WHEN OTHERS
      THEN
         v_sec_cpe := NULL;
         v_error_description := 'NOT OK';
   END get_sec_cpe;

   --Comment END by Sujit Kumar Sahu on 03-May-2010 for fixing SIT Defect Id 70200 - R011

   --Comment BEGIN by Sujit Kumar Sahu on 15-Jun-2010 for fixing Live Defect HSBC - Service Orders for 8CS and HDC  ----  7304 Issue - R011
   PROCEDURE check_mod_invcpe (p_i_order_id            IN     NUMBER,
                               p_i_service_instance    IN     VARCHAR2,
                               p_o_error_description      OUT VARCHAR2,
                               p_i_cpeSeqID            IN     NUMBER)
   IS
      v_inv_orderid   dddca_mod_order.inv_order_id%TYPE;
      v_mod_cpe       dddca_service_instance_cpe.baserouter%TYPE;
      v_inv_cpe       dddca_service_instance_cpe.baserouter%TYPE;
   BEGIN
      BEGIN
         SELECT dddca_mod_order.inv_order_id
           INTO v_inv_orderid
           FROM dddca_mod_order
          WHERE dddca_mod_order.dcomp_order_id = p_i_order_id;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
         WHEN OTHERS
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
      END;

      BEGIN
         SELECT dddca_service_instance_cpe.baserouter
           INTO v_inv_cpe
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = v_inv_orderid
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       p_i_service_instance
                AND dddca_service_instance_cpe.cpeseqid = p_i_cpeSeqID;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_inv_cpe := 'X';
         WHEN OTHERS
         THEN
            v_inv_cpe := 'X';
      END;


      BEGIN
         SELECT dddca_service_instance_cpe.baserouter
           INTO v_mod_cpe
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_service_instance_cpe
          WHERE     dddca_site.order_id = p_i_order_id
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_service_instance_cpe.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       p_i_service_instance
                AND dddca_service_instance_cpe.cpeseqid = p_i_cpeSeqID;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_mod_cpe := 'Y';
         WHEN OTHERS
         THEN
            v_mod_cpe := 'Y';
      END;

      IF v_inv_cpe IS NOT NULL AND v_mod_cpe IS NOT NULL
      THEN
         IF v_inv_cpe = v_mod_cpe
         THEN
            p_o_error_description := 'OK';
            RETURN;
         ELSE
            p_o_error_description := 'NOT OK';
            RETURN;
         END IF;
      ELSE
         p_o_error_description := 'NOT OK';
         RETURN;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_error_description := 'NOT OK';
         RETURN;
      WHEN OTHERS
      THEN
         p_o_error_description := 'NOT OK';
         RETURN;
   END check_mod_invcpe;

   --Comment END by Sujit Kumar Sahu on 15-Jun-2010 for fixing Live Defect HSBC - Service Orders for 8CS and HDC  ----  7304 Issue - R011

   --Comment Start by Sumit Kumar for BTGS-18401_Ultra_Day3 on 21 March 2011 --R14

   PROCEDURE GET_LAN_CARD_DESC (p_i_servInstID   IN     NUMBER,
                                p_i_lanSeqID     IN     NUMBER,
                                p_i_cardPos      IN     VARCHAR2,
                                p_o_cardDesc        OUT VARCHAR2,
                                p_o_errDesc         OUT VARCHAR2)
   IS
      vCardDesc   DDDCA_LAN_CARD_CONFIGURATION.CARD_DESC%TYPE;
   BEGIN
      SELECT CARD_DESC
        INTO vCardDesc
        FROM DDDCA_LAN_CARD_CONFIGURATION DLCC
       WHERE     DLCC.SERVICEINSTANCEID = p_i_servInstID
             AND DLCC.CPESEQID = p_i_lanSeqID
             AND DLCC.CARD_POSITION = p_i_cardPos;


      IF vCardDesc IS NOT NULL
      THEN
         p_o_cardDesc := vCardDesc;
         p_o_errDesc := 'OK';
      ELSE
         p_o_cardDesc := vCardDesc;
         p_o_errDesc := 'NOT OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_errDesc := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errDesc := 'NOT OK';
   END;

   PROCEDURE IS_LAN_CARD_EXIST (p_i_servInstID   IN     NUMBER,
                                p_i_lanSeqID     IN     NUMBER,
                                p_o_count           OUT NUMBER)
   IS
   BEGIN
      SELECT COUNT (*)
        INTO p_o_count
        FROM DDDCA_LAN_CARD_CONFIGURATION DLCC
       WHERE     DLCC.serviceinstanceid = p_i_servInstID
             AND DLCC.cpeseqid = p_i_lanSeqID;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_o_count := 0;
   END;

   PROCEDURE GET_INV_LAN_DIFF (p_i_orderID      IN     NUMBER,
                               p_i_servInstID   IN     NUMBER,
                               p_i_servInst     IN     VARCHAR2,
                               p_i_lanSeqID     IN     NUMBER,
                               p_o_isChange        OUT VARCHAR2,
                               p_o_errDesc         OUT VARCHAR2)
   IS
      vInvOrderId        dddca_mod_order.inv_order_id%TYPE;
      vInvServInstID     dddca_site_service_instance.serviceinstanceid%TYPE;
      vInvLAN            dddca_lan_switch_cpe.lanswitch%TYPE;
      vInvLANUsage       dddca_lan_switch_cpe.cpeusage%TYPE;
      vInvInterface      dddca_serv_inst_accesstype.interfacetype%TYPE;


      vDcompLAN          dddca_lan_switch_cpe.lanswitch%TYPE;
      vDcompLANUsage     dddca_lan_switch_cpe.cpeusage%TYPE;
      vInterface         dddca_serv_inst_accesstype.interfacetype%TYPE;

      vIsLANChng         BOOLEAN DEFAULT FALSE;
      vIsInterfaceChng   BOOLEAN DEFAULT FALSE;
   BEGIN
      SELECT DMO.inv_order_id
        INTO vInvOrderId
        FROM dddca_mod_order DMO
       WHERE DMO.dcomp_order_id = p_i_orderid;

      SELECT DSSI.serviceinstanceid
        INTO vInvServInstID
        FROM dddca_site DS,
             dddca_site_location DSL,
             dddca_site_service_instance DSSI
       WHERE     DS.order_id = vInvOrderId
             AND DS.site_id = DSL.site_id
             AND DSL.site_location_id = DSSI.site_location_id
             AND UPPER (REPLACE (DSSI.serviceinstance, 'Ultra-', '')) =
                    UPPER (REPLACE (p_i_servInst, 'Ultra-', '')); -- Rajesh- Defect 5097- Service instance change from Primary/Secondary to Ultra-Primary/Ultra-Secondary

      --get lan switch foor inventory and amend order
      GET_LAN_SWITCH (vInvServInstID,
                      p_i_lanSeqID,
                      vInvLAN,
                      vInvLANUsage);
      GET_LAN_SWITCH (p_i_servInstID,
                      p_i_lanSeqID,
                      vDcompLAN,
                      vDcompLANUsage);


      IF NVL (vDcompLAN, '') <> NVL (vInvLAN, '')
      THEN
         vIsLANChng := TRUE;
      ELSE
         vIsLANChng := FALSE;
      END IF;

      IF (vInvLANUsage IS NOT NULL AND vDcompLANUsage IS NOT NULL)
      THEN
         IF UPPER (vInvLANUsage) = 'SECONDARY'
         THEN
            GET_INTERFACE_TYPE (vInvServInstID, vInvInterface);
            GET_INTERFACE_TYPE (p_i_servInstID, vInterface);

            IF NVL (vInvInterface, '') <> NVL (vInterface, '')
            THEN
               vIsInterfaceChng := TRUE;
            ELSE
               vIsInterfaceChng := FALSE;
            END IF;          -- IF NVL(vInvInterface,'') <> NVL(vInterface,'')
         ELSE
            vIsInterfaceChng := FALSE; --AS if it is cascaded Router, no need to check for Cascaded Switch.
         END IF;                        --IF upper(vInvLANUsage) = 'SECONDARY'
      END IF;   --IF (vInvLANUsage IS NOT NULL AND vDcompLANUsage IS NOT NULL)


      -- If both Interface and Switch is changed then only
      IF vIsLANChng = FALSE AND vIsInterfaceChng = FALSE
      THEN
         p_o_isChange := 'NO';
      ELSE
         p_o_isChange := 'YES';
      END IF;

      p_o_errDesc := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_o_errDesc := 'NOT OK';
   END;

   PROCEDURE GET_LAN_SWITCH (p_i_servInstID   IN     NUMBER,
                             p_i_lanSeqId     IN     NUMBER,
                             p_i_lanSwitch       OUT VARCHAR2,
                             p_i_lanUsage        OUT VARCHAR2)
   IS
   BEGIN
      SELECT DLSC.lanswitch, DLSC.cpeusage
        INTO p_i_lanSwitch, p_i_lanUsage
        FROM dddca_lan_switch_cpe DLSC
       WHERE     DLSC.serviceinstanceid = p_i_servInstID
             AND DLSC.cpeseqid = p_i_lanSeqId;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_i_lanSwitch := NULL;
         p_i_lanUsage := NULL;
   END GET_LAN_SWITCH;

   PROCEDURE GET_INTERFACE_TYPE (p_i_servInstID     IN     NUMBER,
                                 p_o_InerfaceType      OUT VARCHAR2)
   IS
   BEGIN
      SELECT DSIA.interfacetype
        INTO p_o_InerfaceType
        FROM dddca_serv_inst_accesstype DSIA
       WHERE DSIA.serviceinstanceid = p_i_servInstID;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_o_InerfaceType := NULL;
   END;

   --Comment End by Sumit Kumar for BTGS-18401_Ultra_Day3 on 21 March 2011 --R14
   ---Added by Kanakshree for R16_GSCE_62414
   PROCEDURE GET_LPortType (p_i_serviceinstanceid   IN     NUMBER,
                            p_i_elementtype         IN     VARCHAR2,
                            p_o_porttype               OUT VARCHAR2,
                            p_o_errordescription       OUT VARCHAR2,
                            p_i_elementname         IN     VARCHAR2, --Defect 4923
                            p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10) DEFAULT 1;
      v_card_desc    DDDCA_EXTEND_PORTFOLIO_SWITCH.elementname%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM DDDCA_EXTEND_PORTFOLIO_SWITCH
          WHERE     DDDCA_EXTEND_PORTFOLIO_SWITCH.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId
                AND UPPER (DDDCA_EXTEND_PORTFOLIO_SWITCH.ELEMENTTYPE) =
                       UPPER (p_i_elementtype)                   --Defect 4923
                AND ELEMENTNAME = p_i_elementname;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := 0;
         WHEN OTHERS
         THEN
            v_count_rows := 0;
      END;

      IF v_count_rows > 0
      THEN
         BEGIN
            SELECT DDDCA_EXTEND_PORTFOLIO_SWITCH.PORT_TYPE
              INTO v_card_desc
              FROM DDDCA_EXTEND_PORTFOLIO_SWITCH
             WHERE     DDDCA_EXTEND_PORTFOLIO_SWITCH.serviceinstanceid =
                          p_i_serviceinstanceid
                   AND CPESEQID = p_i_SeqId
                   AND DDDCA_EXTEND_PORTFOLIO_SWITCH.ELEMENTTYPE =
                          p_i_elementtype                        --Defect 4923
                   AND ELEMENTNAME = p_i_elementname;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               v_card_desc := NULL;
            WHEN OTHERS
            THEN
               v_card_desc := NULL;
         END;
      ELSE
         v_card_desc := NULL;
      END IF;

      IF v_card_desc IS NOT NULL
      THEN
         p_o_porttype := v_card_desc;
         p_o_errordescription := 'OK';
      ELSE
         p_o_porttype := v_card_desc;
         p_o_errordescription := 'NOT OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END GET_LPortType;

   ---------------
   ---Added by Kanakshree for R16_GSCE_62414
   PROCEDURE GET_PortType (p_i_serviceinstanceid   IN     NUMBER,
                           p_i_elementtype         IN     VARCHAR2,
                           p_o_porttype               OUT VARCHAR2,
                           p_o_errordescription       OUT VARCHAR2,
                           p_i_elementname         IN     VARCHAR2, --Defect 4923
                           p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10) DEFAULT 1;
      v_card_desc    DDDCA_EXTENDED_PORTFOLIO.elementname%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM DDDCA_EXTENDED_PORTFOLIO
          WHERE     DDDCA_EXTENDED_PORTFOLIO.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId
                AND UPPER (DDDCA_EXTENDED_PORTFOLIO.ELEMENTTYPE) =
                       UPPER (p_i_elementtype)
                AND ELEMENTNAME = p_i_elementname;               --Defect 4923
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := 0;
         WHEN OTHERS
         THEN
            v_count_rows := 0;
      END;

      IF v_count_rows > 0
      THEN
         BEGIN
            SELECT DDDCA_EXTENDED_PORTFOLIO.PORT_TYPE
              INTO v_card_desc
              FROM DDDCA_EXTENDED_PORTFOLIO
             WHERE     DDDCA_EXTENDED_PORTFOLIO.serviceinstanceid =
                          p_i_serviceinstanceid
                   AND CPESEQID = p_i_SeqId
                   AND DDDCA_EXTENDED_PORTFOLIO.ELEMENTTYPE = p_i_elementtype --Defect 4923
                   AND ELEMENTNAME = p_i_elementname;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               v_card_desc := NULL;
            WHEN OTHERS
            THEN
               v_card_desc := NULL;
         END;
      ELSE
         v_card_desc := NULL;
      END IF;

      IF v_card_desc IS NOT NULL
      THEN
         p_o_porttype := v_card_desc;
         p_o_errordescription := 'OK';
      ELSE
         p_o_porttype := v_card_desc;
         p_o_errordescription := 'NOT OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END GET_PortType;

   --------------------
   PROCEDURE GET_CPE_CARD (p_i_serviceinstanceid   IN     NUMBER,
                           p_i_SeqId               IN     NUMBER,
                           p_o_error_flag             OUT VARCHAR2,
                           p_o_error_message          OUT VARCHAR2,
                           p_o_extendedcards          OUT SYS_REFCURSOR)
   IS
      v_count_rows   NUMBER (10) DEFAULT 1;
   BEGIN
      SELECT COUNT (*)
        INTO v_count_rows
        FROM dddca_extended_portfolio
       WHERE     dddca_extended_portfolio.serviceinstanceid =
                    p_i_serviceinstanceid
             AND CPESEQID = p_i_SeqId;

      IF v_count_rows > 0
      THEN
         OPEN p_o_extendedcards FOR
            SELECT ELEMENTTYPE,
                   SUBELEMENTTYPE,
                   ELEMENTNAME,
                   PORT_TYPE
              FROM dddca_extended_portfolio
             WHERE     dddca_extended_portfolio.serviceinstanceid =
                          p_i_serviceinstanceid
                   AND CPESEQID = p_i_SeqId;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_o_error_flag := -1;
         p_o_error_message :=
            'Error encountered while executing the Procedure GET_CPE_CARD(p_i_serviceinstanceid,p_i_SeqId,p_o_error_flag,p_o_error_message)';
   END GET_CPE_CARD;

   ---Added by Kanakshree for GSCE 62414 Incremental Hardware
   PROCEDURE GET_LAN_CARD (p_i_serviceinstanceid   IN     NUMBER,
                           p_i_SeqId               IN     NUMBER,
                           p_o_error_flag             OUT VARCHAR2,
                           p_o_error_message          OUT VARCHAR2,
                           p_o_extendedcards          OUT SYS_REFCURSOR)
   IS
      v_count_rows   NUMBER (10) DEFAULT 1;
   BEGIN
      SELECT COUNT (*)
        INTO v_count_rows
        FROM dddca_extend_portfolio_switch
       WHERE     dddca_extend_portfolio_switch.serviceinstanceid =
                    p_i_serviceinstanceid
             AND CPESEQID = p_i_SeqId;

      IF v_count_rows > 0
      THEN
         OPEN p_o_extendedcards FOR
            SELECT ELEMENTTYPE,
                   SUBELEMENTTYPE,
                   ELEMENTNAME,
                   PORT_TYPE
              FROM dddca_extend_portfolio_switch
             WHERE     dddca_extend_portfolio_switch.serviceinstanceid =
                          p_i_serviceinstanceid
                   AND CPESEQID = p_i_SeqId;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_o_error_flag := -1;
         p_o_error_message :=
            'Error encountered while executing the Procedure GET_CPE_CARD(p_i_serviceinstanceid,p_i_SeqId,p_o_error_flag,p_o_error_message)';
   END GET_LAN_CARD;

   ---Added by Kanakshree for R16_GSCE_62414
   PROCEDURE IS_EXTSPORT_NOTEXISTS (p_i_serviceinstanceid   IN     NUMBER,
                                    p_o_errordescription       OUT VARCHAR2,
                                    p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_extend_portfolio_switch
          WHERE     dddca_extend_portfolio_switch.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         p_o_errordescription := 'NOT OK';
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END IS_EXTSPORT_NOTEXISTS;

   ---Added by Kanakshree for R16_GSCE_62414
   PROCEDURE IS_EXTPORT_NOTEXISTS (p_i_serviceinstanceid   IN     NUMBER,
                                   p_o_errordescription       OUT VARCHAR2,
                                   p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_extended_portfolio
          WHERE     dddca_extended_portfolio.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         p_o_errordescription := 'NOT OK';
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END IS_EXTPORT_NOTEXISTS;

   ---Added by Kanakshree for R16_GSCE_62414

   ---Added by Surendra for R19_GSCE_67459
   PROCEDURE check_mod_invdevice (p_i_order_id            IN     NUMBER,
                                  p_i_service_instance    IN     VARCHAR2,
                                  p_o_error_description      OUT VARCHAR2,
                                  p_i_cpeSeqID            IN     NUMBER)
   IS
      v_inv_orderid   dddca_mod_order.inv_order_id%TYPE;
      v_mod_device    dddca_other_devices.device%TYPE;
      v_inv_device    dddca_other_devices.device%TYPE;
   BEGIN
      BEGIN
         SELECT dddca_mod_order.inv_order_id
           INTO v_inv_orderid
           FROM dddca_mod_order
          WHERE dddca_mod_order.dcomp_order_id = p_i_order_id;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
         WHEN OTHERS
         THEN
            p_o_error_description := 'NOT OK';
            RETURN;
      END;

      BEGIN
         SELECT dddca_other_devices.device
           INTO v_inv_device
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_other_devices
          WHERE     dddca_site.order_id = v_inv_orderid
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_other_devices.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       p_i_service_instance
                AND dddca_other_devices.cpeseqid = p_i_cpeSeqID;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_inv_device := 'X';
         WHEN OTHERS
         THEN
            v_inv_device := 'X';
      END;


      BEGIN
         SELECT dddca_other_devices.device
           INTO v_mod_device
           FROM dddca_site,
                dddca_site_location,
                dddca_site_service_instance,
                dddca_other_devices
          WHERE     dddca_site.order_id = p_i_order_id
                AND dddca_site.site_id = dddca_site_location.site_id
                AND dddca_site_location.site_location_id =
                       dddca_site_service_instance.site_location_id
                AND dddca_site_service_instance.serviceinstanceid =
                       dddca_other_devices.serviceinstanceid
                AND UPPER (dddca_site_service_instance.serviceinstance) =
                       p_i_service_instance
                AND dddca_other_devices.cpeseqid = p_i_cpeSeqID;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_mod_device := 'Y';
         WHEN OTHERS
         THEN
            v_mod_device := 'Y';
      END;

      IF v_inv_device IS NOT NULL AND v_mod_device IS NOT NULL
      THEN
         IF v_inv_device = v_mod_device
         THEN
            p_o_error_description := 'OK';
            RETURN;
         ELSE
            p_o_error_description := 'NOT OK';
            RETURN;
         END IF;
      ELSE
         p_o_error_description := 'NOT OK';
         RETURN;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_error_description := 'NOT OK';
         RETURN;
      WHEN OTHERS
      THEN
         p_o_error_description := 'NOT OK';
         RETURN;
   END check_mod_invdevice;

   ---Added by Surendra for R19_GSCE_67459

   PROCEDURE IS_DEVICE_CARDNOTEXISTS (
      p_i_serviceinstanceid   IN     NUMBER,
      p_o_errordescription       OUT VARCHAR2,
      p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_other_card_configuration
          WHERE     dddca_other_card_configuration.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         p_o_errordescription := 'NOT OK';
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END IS_DEVICE_CARDNOTEXISTS;

   ---Added by Surendra for R19_GSCE_76099

   PROCEDURE GET_DEVICE_CARDDESCRIPTION (
      p_i_serviceinstanceid   IN     NUMBER,
      p_i_cardposition        IN     VARCHAR2,
      p_o_carddescription        OUT VARCHAR2,
      p_o_errordescription       OUT VARCHAR2,
      p_i_SeqId               IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
      v_card_desc    DDDCA_OTHER_CARD_CONFIGURATION.card_desc%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_other_card_configuration
          WHERE     dddca_other_card_configuration.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId
                AND UPPER (dddca_other_card_configuration.card_position) =
                       UPPER (p_i_cardposition);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := 0;
         WHEN OTHERS
         THEN
            v_count_rows := 0;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         BEGIN
            SELECT dddca_other_card_configuration.card_desc
              INTO v_card_desc
              FROM dddca_other_card_configuration
             WHERE     dddca_other_card_configuration.serviceinstanceid =
                          p_i_serviceinstanceid
                   AND CPESEQID = p_i_SeqId
                   AND dddca_other_card_configuration.card_position =
                          p_i_cardposition;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               v_card_desc := NULL;
            WHEN OTHERS
            THEN
               v_card_desc := NULL;
         END;
      ELSE
         v_card_desc := NULL;
      END IF;

      IF v_card_desc IS NOT NULL
      THEN
         p_o_carddescription := v_card_desc;
         p_o_errordescription := 'OK';
      ELSE
         p_o_carddescription := v_card_desc;
         p_o_errordescription := 'NOT OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_o_errordescription := 'NOT OK';
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
   END GET_DEVICE_CARDDESCRIPTION;

   PROCEDURE IS_DEVICE_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                                   p_inv_serviceinstanceid   IN     NUMBER,
                                   p_o_errordescription         OUT VARCHAR2,
                                   p_i_SeqId                 IN     NUMBER)
   IS
      v_count_rows   NUMBER (10);
      n_cardid       dddca_other_card_configuration.card_id%TYPE;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO v_count_rows
           FROM dddca_other_card_configuration
          WHERE     dddca_other_card_configuration.serviceinstanceid =
                       p_i_serviceinstanceid
                AND CPESEQID = p_i_SeqId;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            v_count_rows := NULL;
         WHEN OTHERS
         THEN
            v_count_rows := NULL;
      END;

      IF v_count_rows IS NOT NULL AND v_count_rows = 0
      THEN
         BEGIN
            FOR c_card
               IN (SELECT *
                     FROM dddca_other_card_configuration
                    WHERE     serviceinstanceid = p_inv_serviceinstanceid
                          AND CPESEQID = p_i_SeqId)
            LOOP
               SELECT seq_cpecard.NEXTVAL INTO n_cardid FROM DUAL;

               c_card.serviceinstanceid := p_i_serviceinstanceid;
               c_card.card_id := n_cardid;

               INSERT INTO dddca_other_card_configuration
                    VALUES c_card;
            END LOOP cpe_card_configuration;

            COMMIT;
            p_o_errordescription := 'OK';
         EXCEPTION
            WHEN VALUE_ERROR
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
            WHEN OTHERS
            THEN
               p_o_errordescription := 'NOT OK';
               ROLLBACK;
         END;
      ELSIF v_count_rows IS NOT NULL AND v_count_rows > 0
      THEN
         p_o_errordescription := 'OK';
      END IF;
   EXCEPTION
      WHEN VALUE_ERROR
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
      WHEN OTHERS
      THEN
         p_o_errordescription := 'NOT OK';
         ROLLBACK;
   END IS_DEVICE_CARDEXISTS;
END PK_MOD_GENERAL;
/