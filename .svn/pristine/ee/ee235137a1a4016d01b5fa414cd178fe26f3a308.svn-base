CREATE OR REPLACE PACKAGE BODY MDSLD.DDDCA_PK_USER_PROFILE
AS
   PROCEDURE dddca_sp_createteamcategory (vteamname         IN     VARCHAR2,
                                          vrceflag          IN     VARCHAR2,
                                          vsubmissionflag   IN     VARCHAR2,
                                          vspringflag       IN     VARCHAR2,
                                          error_desc           OUT VARCHAR2)
   IS
   /******************************************************************************
    Ver    Date        Author      Description
   -----  ----------  ----------  ------------------------------------
   2.0    18/08/2015   Mugilan S   Introduced pagination to list the orders in UI (SP - GETORDERS)
  ******************************************************************************/
   BEGIN
      INSERT INTO dddca_teamcategory (teamcategoryid,
                                      teamcategoryname,
                                      rceflag,
                                      submissionqflag,
                                      springflag)
           VALUES (dddca_teamcategory_seq.NEXTVAL,
                   vteamname,
                   vrceflag,
                   vsubmissionflag,
                   vspringflag);

      COMMIT;
      error_desc := 'OK';
   EXCEPTION
      WHEN DUP_VAL_ON_INDEX
      THEN
         error_desc := 'RECORD ALREADY EXIST';
      WHEN OTHERS
      THEN
         error_desc := 'NOT OK';
   END dddca_sp_createteamcategory;

   -----------------------------------
   PROCEDURE getaccesscontrollist1 (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT accid, accessname
             FROM dddca_access_control
         ORDER BY accessname;

      io_cursor := v_cursor;
   END getaccesscontrollist1;

   ------------------------------------
   PROCEDURE getaccesscontrollist2 (vroleid     IN     NUMBER,
                                    io_cursor      OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT a.accid, a.accessname
             FROM dddca_access_control a, dddca_role_access b
            WHERE a.accid = b.accid AND b.roleid = vroleid
         ORDER BY accessname;

      io_cursor := v_cursor;
   END getaccesscontrollist2;

   ------------------------------------
   PROCEDURE dddca_sp_irole (vrolename    IN     VARCHAR2,
                             nteamid      IN     NUMBER,
                             vroleid         OUT NUMBER,
                             error_desc      OUT VARCHAR2)
   AS
      newroleid   NUMBER;
      icount      NUMBER;
   BEGIN
      SELECT COUNT (*)
        INTO icount
        FROM dddca_role
       WHERE rolename = vrolename;

      IF icount = 0
      THEN
         SELECT dddca_role_seq.NEXTVAL INTO newroleid FROM DUAL;

         IF nteamid > 0
         THEN
            INSERT INTO dddca_role (roleid,
                                    rolename,
                                    teamid,
                                    localglobalflag)
                 VALUES (newroleid,
                         vrolename,
                         nteamid,
                         'L');
         ELSE
            INSERT INTO dddca_role (roleid, rolename, localglobalflag)
                 VALUES (newroleid, vrolename, 'G');
         END IF;

         vroleid := newroleid;
         COMMIT;
         error_desc := 'OK';
      ELSE
         error_desc := 'Role Name is already exits.';
      END IF;
   EXCEPTION
      WHEN DUP_VAL_ON_INDEX
      THEN
         error_desc := 'RECORD ALREADY EXIST';
      WHEN OTHERS
      THEN
         error_desc := 'NOT OK';
   END dddca_sp_irole;

   -----------------------------------------
   PROCEDURE dddca_sp_urole (nroleid      IN     NUMBER,
                             vrolename    IN     VARCHAR2,
                             nteamid      IN     NUMBER,
                             error_desc      OUT VARCHAR2)
   AS
      newroleid   NUMBER;
      icount      NUMBER;
   BEGIN
      SELECT COUNT (*)
        INTO icount
        FROM dddca_role
       WHERE rolename = vrolename AND roleid <> nroleid;

      IF icount = 0
      THEN
         IF nteamid > 0
         THEN
            UPDATE dddca_role
               SET rolename = vrolename,
                   teamid = nteamid,
                   localglobalflag = 'L'
             WHERE roleid = nroleid;
         ELSE
            UPDATE dddca_role
               SET rolename = vrolename, teamid = NULL, localglobalflag = 'G'
             WHERE roleid = nroleid;
         END IF;

         COMMIT;
         error_desc := 'OK';
      ELSE
         error_desc := 'Role Name is already exits.';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         error_desc := SQLERRM;
   END dddca_sp_urole;

   -----------------------------------------
   PROCEDURE dddca_sp_iroleaccess (vroleid      IN     VARCHAR2,
                                   vaccessid    IN     NUMBER,
                                   error_desc      OUT VARCHAR2)
   AS
      newroleid   NUMBER;
   BEGIN
      INSERT INTO dddca_role_access (roleaccessid, roleid, accid)
           VALUES (dddca_roleaccess_seq.NEXTVAL, vroleid, vaccessid);

      COMMIT;
      error_desc := 'OK';
   EXCEPTION
      WHEN DUP_VAL_ON_INDEX
      THEN
         error_desc := 'RECORD ALREADY EXIST';
      WHEN OTHERS
      THEN
         error_desc := 'NOT OK';
   END dddca_sp_iroleaccess;

   -----------------------------------------------
   PROCEDURE dddca_sp_droleaccess (vroleid      IN     VARCHAR2,
                                   error_desc      OUT VARCHAR2)
   AS
      newroleid   NUMBER;
   BEGIN
      DELETE FROM dddca_role_access
            WHERE roleid = vroleid;

      COMMIT;
      error_desc := 'OK';
   EXCEPTION
      WHEN DUP_VAL_ON_INDEX
      THEN
         error_desc := 'RECORD ALREADY EXIST';
      WHEN OTHERS
      THEN
         error_desc := 'NOT OK';
   END dddca_sp_droleaccess;

   -----------------------------------------------
   --InPut Parameters: nTemaID, LocalGlobalFlag
   --             nTeamId        LocalGlobalFlag     OutPut
   ----------------------------------------
   --                0            B       All Role List
   --                0           G        Global Only
   --              Other than 0      B       Local For Team and Global role
   --              Other than 0     L        Local for Team
   PROCEDURE getrolenamelist (nteamid            IN     NUMBER,
                              vlocalglobalflag   IN     CHAR,
                              io_cursor             OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      IF nteamid = 0
      THEN
         IF vlocalglobalflag = 'G'
         THEN
            --ONLY GLOBAL ROLE LIST
            OPEN v_cursor FOR
                 SELECT roleid,
                        rolename,
                        teamid,
                        localglobalflag,
                        dddca_pk_user_pro.teamname (teamid) AS team
                   FROM dddca_role
                  WHERE dddca_role.localglobalflag = 'G'
               ORDER BY rolename;
         ELSE
            --ALL ROLE LIST
            OPEN v_cursor FOR
                 SELECT roleid,
                        rolename,
                        teamid,
                        localglobalflag,
                        dddca_pk_user_pro.teamname (teamid) AS team
                   FROM dddca_role
               ORDER BY rolename;
         END IF;
      ELSE
         IF vlocalglobalflag = 'L'
         THEN
            --LOCAL ONLY
            OPEN v_cursor FOR
               SELECT roleid,
                      rolename,
                      teamid,
                      localglobalflag,
                      dddca_pk_user_pro.teamname (teamid) AS team
                 FROM dddca_role
                WHERE     dddca_role.localglobalflag = 'L'
                      AND dddca_role.teamid = nteamid;
         ELSIF vlocalglobalflag = 'G'
         THEN
            -- GLOBAL ONLY
            OPEN v_cursor FOR
               SELECT roleid,
                      rolename,
                      teamid,
                      localglobalflag,
                      dddca_pk_user_pro.teamname (teamid) AS team
                 FROM dddca_role
                WHERE dddca_role.localglobalflag = 'G';
         ELSE
            -- LOCAL AND GLOBAL BOTH
            OPEN v_cursor FOR
               SELECT roleid,
                      rolename,
                      teamid,
                      localglobalflag,
                      dddca_pk_user_pro.teamname (teamid) AS team
                 FROM dddca_role
                WHERE    (    dddca_role.teamid = nteamid
                          AND localglobalflag = 'L')
                      OR localglobalflag = 'G';
         END IF;
      END IF;

      io_cursor := v_cursor;
   END getrolenamelist;

   ------------------------------------
   PROCEDURE getrolenamelist2 (vroleid     IN     NUMBER,
                               io_cursor      OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT roleid,
                  rolename,
                  teamid,
                  dddca_role.localglobalflag
             FROM dddca_role
            WHERE roleid = vroleid
         ORDER BY rolename;

      io_cursor := v_cursor;
   END getrolenamelist2;

   ---------------------------------------------
   PROCEDURE getcountrylist (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT country_name
             FROM dv_countries
         ORDER BY country_name;

      io_cursor := v_cursor;
   END getcountrylist;

   --
   --Description : Procedure to get country list for the product.
   --
   PROCEDURE getcountrylistbyproduct (vproduct    IN     VARCHAR2,
                                      io_cursor      OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      IF UPPER (vproduct) = 'BT MPLS - STD DSL'
      THEN
         OPEN v_cursor FOR
              SELECT country_name
                FROM dv_countries
               WHERE standard_support = 'Y'
            ORDER BY country_name;
      ELSIF UPPER (vproduct) = 'BT MPLS - XDSL'
      THEN
         OPEN v_cursor FOR
              SELECT country_name
                FROM dv_countries
               WHERE xdsl_support = 'Y'
            ORDER BY country_name;
      ELSIF UPPER (vproduct) = 'SPRING'
      THEN
         OPEN v_cursor FOR
              SELECT country_name
                FROM dv_countries
               WHERE spring_support = 'Y'
            ORDER BY country_name;
      ELSIF UPPER (vproduct) = 'ALL'
      THEN
         OPEN v_cursor FOR
              SELECT country_name
                FROM dv_countries
            ORDER BY country_name;
      ELSE                                                           -- TAHITI
         OPEN v_cursor FOR
              SELECT country_name
                FROM dv_countries
               WHERE tahiti_support = 'Y'
            ORDER BY country_name;
      END IF;

      io_cursor := v_cursor;
   END getcountrylistbyproduct;

   ------------------------------------

   /*PROCEDURE DDDCA_sp_uCountry (vCountry_Name in varchar2, vORTFlag in varchar2, ERROR_DESC OUT varchar2)
   AS
     NEWROLEID NUMBER;
     BEGIN


        Update DV_COUNTRIES
        Set ORTFLAG = vORTFlag
        Where
        COUNTRY_NAME = vCountry_Name;

        COMMIT;
         ERROR_DESC:='OK';

    EXCEPTION
          WHEN OTHERS THEN
              ERROR_DESC:= 'NOT OK';
    END DDDCA_sp_uCountry;

   -----------------------------------------------*/
   PROCEDURE dddca_sp_savereason (vflag             IN     VARCHAR2,
                                  vuserid           IN     NUMBER,
                                  vreason           IN     VARCHAR2,
                                  vorderid          IN     NUMBER,
                                  vteamcategoryid   IN     NUMBER,
                                  vemailid             OUT VARCHAR2,
                                  error_desc           OUT VARCHAR2,
                                  vteamid           IN     NUMBER)
   AS
      vordercreator     NUMBER;
      -- vTEAMID number;
      vmailid           VARCHAR2 (100);
      vcanceledby       NUMBER;
      vcreatorteamid    NUMBER;
      vsdteamid         NUMBER;
      icount            NUMBER;
      vsddefaultowner   NUMBER;
      nproductid        NUMBER;
      vproduct          VARCHAR (255);
      visspring         VARCHAR (5);
      -- vSpringFlag VARCHAR(10);
      vspringflag       NUMBER;
      visfce            VARCHAR (5);      -- Pramod Mathew : FCE Usr Profile--
      vfceflag          NUMBER;          -- Pramod Mathew : FCE User Profile--
      vordertype        VARCHAR2 (20);
      --Pramod Mathew : Cancel Inventory Order too for a Modify Order--
      vinvorderid       VARCHAR2 (20);
   --Pramod Mathew : Cancel Inventory Order too for a Modify Order--
   BEGIN
      IF vteamcategoryid = 2
      THEN
         /*select DISTINCT teamid INTO  vCreatorTeamId
         from DDDCA_ORDER_HISTORY
         WHERE
             ORDER_ID = vOrderId
             and
             DDDCA_ORDER_HISTORY.DELEGATEDBY IS NULL AND
             DDDCA_ORDER_HISTORY.REJECTEDBY IS NULL AND
             DDDCA_ORDER_HISTORY.CANCELEDBY IS NULL AND
             DDDCA_ORDER_HISTORY.ORDERSTATUS = 'SALES IN PROGRESS';
         */
         SELECT salesteam
           INTO vcreatorteamid
           FROM dddca_order_details
          WHERE order_id = vorderid;
      ELSE
         vspringflag := 0;

         --Pramod Mathew : FCE changes
         SELECT COUNT (springflag)
           INTO vspringflag
           FROM dddca_teamcategory
          WHERE teamcategoryid = vteamcategoryid;

         /*        IF vSpringFlag = 'Y' THEN
                     vIsSpring := 'TRUE';
                   ELSE
                     vIsSpring := 'FALSE';
                   END IF;*/
         IF vspringflag > 0
         THEN
            visspring := 'TRUE';
         ELSE
            visspring := 'FALSE';
         END IF;

         vfceflag := 0;

         SELECT COUNT (fce_flag)
           INTO vfceflag
           FROM dddca_teamcategory
          WHERE teamcategoryid = vteamcategoryid;

         IF vfceflag > 0
         THEN
            visfce := 'TRUE';
         ELSE
            visfce := 'FALSE';
         END IF;
      END IF;

      IF UPPER (vflag) = 'CANCEL'
      THEN
         proc_cpe_supplier.cancel_supplier (vorderid);

         IF vteamcategoryid = 1
         THEN
            SELECT creator_person_id
              INTO vordercreator
              FROM dddca_order_details
             WHERE order_id = vorderid;

            SELECT email
              INTO vemailid
              FROM dddca_sysuser
             WHERE sysuserid = vordercreator;

            UPDATE dddca_order_details
               SET orderstatus = 'CANCELED ORDER',
                   canceledby = vuserid,
                   cancelreason = vreason,
                   canceledbyteam = vteamid,
                   delegatedto = NULL,
                   delegatedby = NULL,
                   delegatereason = NULL,
                   delegatedtoteamid = NULL,
                   delegatedbyteamid = NULL
             WHERE order_id = vorderid;

            COMMIT;
            error_desc := 'OK';
         ELSIF vteamcategoryid = 2
         THEN
            SELECT creator_person_id
              INTO vordercreator
              FROM dddca_order_details
             WHERE order_id = vorderid;

            SELECT email
              INTO vemailid
              FROM dddca_sysuser
             WHERE sysuserid = vordercreator;

            UPDATE dddca_order_details
               SET orderstatus = 'CANCELED BY SD',
                   teamid = vcreatorteamid,
                   --ORDEROWNER = vORDERCREATOR,
                   canceledby = vuserid,
                   cancelreason = vreason,
                   canceledbyteam = vteamid,
                   delegatedto = NULL,
                   delegatedby = NULL,
                   delegatereason = NULL,
                   delegatedtoteamid = NULL,
                   delegatedbyteamid = NULL
             WHERE order_id = vorderid;

            COMMIT;
            error_desc := 'OK';
         ELSIF visspring = 'TRUE'
         THEN
            SELECT orderowner
              INTO vordercreator
              FROM dddca_order_details
             WHERE order_id = vorderid;

            SELECT DISTINCT ordertype
              INTO vordertype
              FROM dddca_customer
             WHERE order_id = vorderid;                   --Pramod Mathew ----

            SELECT email
              INTO vemailid
              FROM dddca_sysuser
             WHERE sysuserid = vordercreator;

            UPDATE dddca_order_details
               SET orderstatus = 'CANCELED ORDER',
                   canceledby = vuserid,
                   cancelreason = vreason,
                   canceledbyteam = vteamid,
                   delegatedto = NULL,
                   delegatedby = NULL,
                   delegatereason = NULL,
                   delegatedtoteamid = NULL,
                   delegatedbyteamid = NULL
             WHERE order_id = vorderid;

            --Pramod Mathew-- : Cancel Inventory Order too for a Modify Order------------------------
            IF    UPPER (vordertype) = 'MODIFY'
               OR UPPER (vordertype) = 'CEASE'
               OR UPPER (vordertype) = 'INFLIGHT'
               OR UPPER (vordertype) = 'MODIFY RCD ONLY'
            THEN
               SELECT inv_order_id
                 INTO vinvorderid
                 FROM dddca_mod_order
                WHERE dcomp_order_id = vorderid;

               UPDATE dddca_order_details
                  SET orderstatus = 'CANCELED ORDER',
                      canceledby = vuserid,
                      cancelreason = vreason,
                      canceledbyteam = vteamid,
                      delegatedto = NULL,
                      delegatedby = NULL,
                      delegatereason = NULL,
                      delegatedtoteamid = NULL,
                      delegatedbyteamid = NULL
                WHERE order_id = vinvorderid;
            END IF;

            --Pramod Mathew------ : Cancel Inventory Order too for a Modify Order-------------------
            COMMIT;
            error_desc := 'OK';
         -- Pramod Mathew FCE----
         ELSIF visfce = 'TRUE'
         THEN
            SELECT orderowner
              INTO vordercreator
              FROM dddca_order_details
             WHERE order_id = vorderid;

            SELECT email
              INTO vemailid
              FROM dddca_sysuser
             WHERE sysuserid = vordercreator;

            UPDATE dddca_order_details
               SET orderstatus = 'CANCELED ORDER',
                   canceledby = vuserid,
                   cancelreason = vreason,
                   canceledbyteam = vteamid,
                   delegatedto = NULL,
                   delegatedby = NULL,
                   delegatereason = NULL,
                   delegatedtoteamid = NULL,
                   delegatedbyteamid = NULL
             WHERE order_id = vorderid;

            COMMIT;
            error_desc := 'OK';
         -- Pramod Mathew FCE----
         /*    elsif vTeamCategoryId = 4 then

                  select EMAIL into vEmailId from DDDCA_SYSUSER Where SYSUSERID = vORDERCREATOR;

                  Update DDDCA_ORDER_DETAILS set
                  ORDERSTATUS = 'CANCELED BY RCE PUNE',
                  TEAMID = vTEAMID,
                  ORDEROWNER = vORDERCREATOR,
                  CANCELEDBY = vUserId,
                  CANCELREASON = vReason
                  Where ORDER_ID = vOrderId;

                  COMMIT;
                  ERROR_DESC:='OK';

               elsif vTeamCategoryId = 5 then

                  select EMAIL into vEmailId from DDDCA_SYSUSER Where SYSUSERID = vORDERCREATOR;

                  Update DDDCA_ORDER_DETAILS set
                  ORDERSTATUS = 'CANCELED BY RCE BUDAPEST',
                  TEAMID = vTEAMID,
                  ORDEROWNER = vORDERCREATOR,
                  CANCELEDBY = vUserId,
                  CANCELREASON = vReason
                  Where ORDER_ID = vOrderId;

                  COMMIT;
                  ERROR_DESC:='OK';
            */
         END IF;
      ELSIF UPPER (vflag) = 'REJECT'
      THEN
         SELECT creator_person_id
           INTO vordercreator
           FROM dddca_order_details
          WHERE order_id = vorderid;

         SELECT email
           INTO vemailid
           FROM dddca_sysuser
          WHERE sysuserid = vordercreator;

         UPDATE dddca_order_details
            SET orderstatus = 'REJECTED TO SALES',
                teamid = vcreatorteamid,
                --ORDEROWNER = vORDERCREATOR,
                rejectedby = vuserid,
                rejectreason = vreason,
                rejectedbyteam = vteamid,
                delegatedto = NULL,
                delegatedby = NULL,
                delegatereason = NULL,
                delegatedtoteamid = NULL,
                delegatedbyteamid = NULL
          WHERE order_id = vorderid;

         COMMIT;
         error_desc := 'OK';
      ELSIF UPPER (vflag) = 'REJECTTOSD'
      THEN
         SELECT orderowner
           INTO vordercreator
           FROM dddca_order_details
          WHERE order_id = vorderid;

         --Check SD Team
         --IF SD Team is null then get rejected team from submission queue and
         --reject to that
         SELECT COUNT (*)
           INTO icount
           FROM dddca_order_details
          WHERE order_id = vorderid AND sdteam IS NULL;

         IF icount > 0
         THEN
            --Get Product Id
            SELECT access_type
              INTO vproduct
              FROM dddca_order_product
             WHERE order_id = vorderid;

            IF UPPER (vproduct) = 'XDSL'
            THEN
               nproductid := 1;
            ELSE
               nproductid := 2;
            END IF;

            SELECT salesteam
              INTO vcreatorteamid
              FROM dddca_order_details
             WHERE order_id = vorderid;

            SELECT rejectqueueid, rejectdefaultowner
              INTO vsdteamid, vsddefaultowner
              FROM dddca_submission_queue
             WHERE     teamid = vcreatorteamid
                   AND queueid = (SELECT teamid
                                    FROM dddca_order_details
                                   WHERE order_id = vorderid)
                   AND productid = nproductid;
         ELSE
            SELECT sdteam
              INTO vsdteamid
              FROM dddca_order_details
             WHERE order_id = vorderid;

            SELECT orderowner
              INTO vsddefaultowner
              FROM dddca_order_details
             WHERE order_id = vorderid;
         END IF;

         SELECT email
           INTO vemailid
           FROM dddca_sysuser
          WHERE sysuserid = vsddefaultowner;

         UPDATE dddca_order_details
            SET orderstatus = 'REJECTED TO SD',
                teamid = vsdteamid,
                orderowner = vsddefaultowner,
                --ORDEROWNER = vORDERCREATOR,
                rejectedby = vuserid,
                delegatedto = NULL,
                delegatedby = NULL,
                delegatereason = NULL,
                delegatedtoteamid = NULL,
                delegatedbyteamid = NULL,
                rejectreason = vreason,
                rejectedbyteam = vteamid
          WHERE order_id = vorderid;

         COMMIT;
         error_desc := 'OK';
      ELSIF UPPER (vflag) = 'REJECTCANCELED'
      THEN
         SELECT canceledby
           INTO vcanceledby
           FROM dddca_order_details
          WHERE order_id = vorderid;

         SELECT canceledbyteam
           INTO vsdteamid
           FROM dddca_order_details
          WHERE order_id = vorderid;

         /*select DISTINCT teamid INTO  vSDTeamId
         from DDDCA_ORDER_HISTORY
         WHERE
             ORDER_ID = vOrderId
             and
             DDDCA_ORDER_HISTORY.DELEGATEDBY IS NULL AND
             dddca_order_history.delegatedto is null and
             DDDCA_ORDER_HISTORY.REJECTEDBY IS NULL AND
             DDDCA_ORDER_HISTORY.CANCELEDBY IS NULL AND
             DDDCA_ORDER_HISTORY.ORDERSTATUS = 'SUBMIT TO SD'; */
         SELECT email
           INTO vemailid
           FROM dddca_sysuser
          WHERE sysuserid = vcanceledby;

         UPDATE dddca_order_details
            SET orderstatus = 'SUBMIT TO SD',
                teamid = vsdteamid,
                orderowner = vcanceledby,
                delegatedto = NULL,
                delegatedby = NULL,
                delegatereason = NULL,
                delegatedtoteamid = NULL,
                delegatedbyteamid = NULL,
                rejectedbyteam = NULL,
                canceledbyteam = NULL,
                canceledby = NULL,
                cancelreason = NULL,
                approvedreason = vreason
          WHERE order_id = vorderid;

         COMMIT;
         error_desc := 'OK';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         error_desc := SQLERRM;
   END dddca_sp_savereason;

   ------------------------------------------------
   PROCEDURE getadminemaillist (vorderid    IN     NUMBER,
                                io_cursor      OUT t_cursor,
                                vteamid     IN     NUMBER)
   IS
      vordercreator   NUMBER;
      --       vTEAMID number;
      v_cursor        t_cursor;
   BEGIN
      SELECT creator_person_id
        INTO vordercreator
        FROM dddca_order_details
       WHERE order_id = vorderid;

      /*       select a.TEAMID into vTEAMID from DDDCA_TEAM a, DDDCA_SYSUSER b, DDDCA_TEAMCOUNTRIES c, DDDCA_TEAM_SYSUSER d
               WHERE
               b.SYSUSERID = vORDERCREATOR and
               a.TEAMID = c.TEAMID and
               c.TEAMCOUNTRYID = d.TEAMCOUNTRYID and
               b.SYSUSERID = d.SYSUSERID; */
      OPEN v_cursor FOR
         /*      Select Email from DDDCA_SYSUSER a, DDDCA_TEAM b, DDDCA_TEAMCOUNTRIES c,
                 DDDCA_TEAM_SYSUSER d, DDDCA_ROLE e, DDDCA_ROLE_PROFILE f
                 WHERE
         --      a.SYSUSERID = vORDERCREATOR and
                 b.TEAMID = c.TEAMID and
                 c.TEAMID = vTEAMID and
                 c.TEAMCOUNTRYID = d.TEAMCOUNTRYID and
                 a.SYSUSERID = d.SYSUSERID and
                 d.PROFILEID = f.PROFILEID and
                 e.ROLEID =f.ROLEID and
                 upper(e.ROLENAME) = 'TEAM ADMIN'; */
         SELECT DISTINCT email
           FROM dddca_sysuser a,
                dddca_team b,
                dddca_teamcountries c,
                dddca_profile d,
                dddca_role e,
                dddca_role_profile f,
                dddca_user_profile g
          WHERE     a.sysuserid = g.sysuserid
                AND g.profileid = d.profileid
                AND g.teamid = b.teamid
                AND b.teamid = c.teamid
                AND c.teamid = vteamid
                AND d.profileid = f.profileid
                AND e.roleid = f.roleid
                AND UPPER (e.rolename) LIKE '%ADMIN%'
                AND UPPER (e.rolename) <> 'System Admin';

      io_cursor := v_cursor;
   END getadminemaillist;

   ------------------------------------
   PROCEDURE getuserlist (vuserid      IN     NUMBER,
                          vorderid     IN     NUMBER,
                          vteamid      IN     NUMBER,
                          vortflag     IN     VARCHAR2,
                          io_cursor       OUT t_cursor,
                          error_desc      OUT VARCHAR2)
   IS
      vteamcategoryid   NUMBER;
      vcount            NUMBER;
      v_cursor          t_cursor;
      icount            NUMBER;
      newteamcategory   NUMBER;
      vaafdel           NUMBER;
      vcountaafdel      NUMBER;
   BEGIN
      error_desc := 'Here';

      SELECT COUNT (*)
        INTO vcount
        FROM dddca_order_details
       WHERE delegatedto = vuserid AND order_id = vorderid;

      SELECT dddca_team.teamcategoryid
        INTO vteamcategoryid
        FROM dddca_team
       WHERE dddca_team.teamid = vteamid;

      newteamcategory := vteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = vteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = vteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      vteamcategoryid := newteamcategory;

      IF vcount = 0
      THEN
         SELECT COUNT (*)
           INTO vcountaafdel
           FROM dddca_aaf_delegation
          WHERE edca_order_id = vorderid;

         IF vcountaafdel > 0
         THEN
            -- HERE
            SELECT finaldelegationrequired
              INTO vaafdel
              FROM dddca_aaf_delegation
             WHERE edca_order_id = vorderid;

            IF vortflag = 'N'
            THEN
               --NON ORT User List for Team -
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE     b.sysuserid = c.orderowner
                            AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSIF vortflag = 'Y'
            THEN
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE     b.sysuserid = c.orderowner
                            AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            END IF;
         ELSE
            IF vortflag = 'N'
            THEN
               --NON ORT User List for Team -
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               /*IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE b.sysuserid = c.orderowner AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE*/
               OPEN v_cursor FOR
                  SELECT DISTINCT
                         dddca_sysuser.sysuserid,
                         dddca_sysuser.username,
                         dddca_sysuser.email
                    FROM dddca_access_control,
                         dddca_role_access,
                         dddca_role_profile,
                         dddca_user_profile,
                         dddca_sysuser
                   WHERE     dddca_access_control.accid =
                                dddca_role_access.accid
                         AND dddca_role_access.roleid =
                                dddca_role_profile.roleid
                         AND dddca_role_profile.profileid =
                                dddca_user_profile.profileid
                         AND dddca_user_profile.sysuserid =
                                dddca_sysuser.sysuserid
                         AND dddca_user_profile.teamid = vteamid
                         AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                         dddca_role_profile.profileid
                                                                    FROM dddca_access_control,
                                                                         dddca_role_access,
                                                                         dddca_role_profile,
                                                                         dddca_user_profile,
                                                                         dddca_sysuser
                                                                   WHERE     dddca_access_control.accid =
                                                                                dddca_role_access.accid
                                                                         AND dddca_role_access.roleid =
                                                                                dddca_role_profile.roleid
                                                                         AND dddca_role_profile.profileid =
                                                                                dddca_user_profile.profileid
                                                                         AND dddca_user_profile.sysuserid =
                                                                                dddca_sysuser.sysuserid
                                                                         AND UPPER (
                                                                                accessname) =
                                                                                UPPER (
                                                                                   'Access ORT Countries'))
                         AND dddca_sysuser.sysuserid <> vuserid
                  UNION
                  SELECT NULL AS sysuserid,
                         'Others' AS username,
                         NULL AS email
                    FROM DUAL
                  ORDER BY username;
            -- END IF;
            ELSIF vortflag = 'Y'
            THEN
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               /*IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE b.sysuserid = c.orderowner AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE*/
               --ORT User List for Team -
               OPEN v_cursor FOR
                  SELECT DISTINCT
                         dddca_sysuser.sysuserid,
                         dddca_sysuser.username,
                         dddca_sysuser.email
                    FROM dddca_access_control,
                         dddca_role_access,
                         dddca_role_profile,
                         dddca_user_profile,
                         dddca_sysuser
                   WHERE     dddca_access_control.accid =
                                dddca_role_access.accid
                         AND dddca_role_access.roleid =
                                dddca_role_profile.roleid
                         AND dddca_role_profile.profileid =
                                dddca_user_profile.profileid
                         AND dddca_user_profile.sysuserid =
                                dddca_sysuser.sysuserid
                         AND UPPER (accessname) =
                                UPPER ('Access ORT Countries')
                         AND dddca_user_profile.teamid = vteamid
                         AND dddca_sysuser.sysuserid <> vuserid
                  UNION
                  SELECT NULL AS sysuserid,
                         'Others' AS username,
                         NULL AS email
                    FROM DUAL
                  ORDER BY username;
            --END IF;
            END IF;
         END IF;
      ELSIF vcount > 0
      THEN
         IF vteamcategoryid = 1
         THEN
            SELECT COUNT (*)
              INTO vcount
              FROM dddca_order_details
             WHERE order_id = vorderid AND delegatedto = creator_person_id;

            IF vcount > 0
            THEN                                      --Delegated back to self
               --Display all users
               ---HERE
               IF vortflag = 'N'
               THEN
                  --NON ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               ELSIF vortflag = 'Y'
               THEN
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSE
               OPEN v_cursor FOR
                  SELECT b.sysuserid, b.username, b.email
                    FROM dddca_sysuser b, dddca_order_details c
                   WHERE     b.sysuserid = c.creator_person_id
                         AND c.order_id = vorderid;
            END IF;
         ELSIF vteamcategoryid = 2 OR vteamcategoryid = 3
         THEN
            SELECT COUNT (*)
              INTO vcount
              FROM dddca_order_details
             WHERE order_id = vorderid AND delegatedto = orderowner;

            IF vcount > 0
            THEN                                      --Delegated back to self
               --Display all users
               ---HERE
               IF vortflag = 'N'
               THEN
                  --NON ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               ELSIF vortflag = 'Y'
               THEN
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSE
               OPEN v_cursor FOR
                  SELECT b.sysuserid, b.username, b.email
                    FROM dddca_sysuser b, dddca_order_details c
                   WHERE b.sysuserid = c.orderowner AND c.order_id = vorderid;
            END IF;
         ELSIF vteamcategoryid = 5 OR vteamcategoryid = 4
         THEN
            SELECT COUNT (*)
              INTO vcount
              FROM dddca_order_details
             WHERE order_id = vorderid AND delegatedto = rceowner;

            IF vcount > 0
            THEN                                      --Delegated back to self
               --Display all users
               ---HERE
               IF vortflag = 'N'
               THEN
                  --NON ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               ELSIF vortflag = 'Y'
               THEN
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSE
               OPEN v_cursor FOR
                  SELECT b.sysuserid, b.username, b.email
                    FROM dddca_sysuser b, dddca_order_details c
                   WHERE b.sysuserid = c.rceowner AND c.order_id = vorderid;
            END IF;
         END IF;
      END IF;

      io_cursor := v_cursor;
   EXCEPTION
      WHEN OTHERS
      THEN
         error_desc := SQLERRM;
   END getuserlist;

   ----------------------------------------
   PROCEDURE dddca_sp_savedeligation (vuserid            IN     NUMBER,
                                      vdelegateuserid    IN     NUMBER,
                                      vreason            IN     VARCHAR2,
                                      vorderid           IN     NUMBER,
                                      vteamcategoryid    IN     NUMBER,
                                      ndelegatedtoteam   IN     NUMBER,
                                      ndelegatedbyteam   IN     NUMBER,
                                      error_desc            OUT VARCHAR2)
   AS
      vordercreator   NUMBER;
      vteamid         NUMBER;
      vmailid         VARCHAR2 (100);
      vcanceledby     NUMBER;
   BEGIN
      --    select a.TEAMID into vTEAMID from DDDCA_TEAM a, DDDCA_SYSUSER b, DDDCA_TEAMCOUNTRIES c, DDDCA_TEAM_SYSUSER d
      --    WHERE
      --    b.SYSUSERID = vUserId and
      --    a.TEAMID = c.TEAMID and
      --    c.TEAMCOUNTRYID = d.TEAMCOUNTRYID and
      --    b.SYSUSERID = d.SYSUSERID;

      --    If vTeamCategoryId = 1 then
      IF ndelegatedtoteam = 0
      THEN
         UPDATE dddca_order_details
            SET delegatedto = vdelegateuserid,
                delegatedby = vuserid,
                delegatedtoteamid = NULL,
                delegatedbyteamid = ndelegatedbyteam,
                delegatereason = vreason
          WHERE order_id = vorderid;
      ELSE
         UPDATE dddca_order_details
            SET delegatedto = vdelegateuserid,
                delegatedby = vuserid,
                delegatedtoteamid = ndelegatedtoteam,
                delegatedbyteamid = ndelegatedbyteam,
                delegatereason = vreason
          WHERE order_id = vorderid;
      END IF;

      COMMIT;
      error_desc := 'OK';
   --    elsif vTeamCategoryId = 2 then

   --       Update DDDCA_ORDER_DETAILS set
   --          DELEGATEDTO = vDelegateUserId,
   --       DELEGATEDBY = vUserId,
   --       TEAMID= vTEAMID,
   --       ORDEROWNER = vDelegateUserId,
   --       DELEGATEREASON = vReason
   --       Where ORDER_ID = vOrderId;

   --       COMMIT;
   --       ERROR_DESC:='OK';

   --    End if;
   EXCEPTION
      WHEN OTHERS
      THEN
         error_desc := SQLERRM;
   END dddca_sp_savedeligation;

   -----------------------------------
   PROCEDURE getteamcategorylist (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT teamcategoryid,
                  teamcategoryname,
                  rceflag,
                  submissionqflag
             FROM dddca_teamcategory
         ORDER BY teamcategoryname;

      io_cursor := v_cursor;
   END getteamcategorylist;

   -----------------------------------
   PROCEDURE getrolelist (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT roleid, rolename
             FROM dddca_role
         ORDER BY rolename;

      io_cursor := v_cursor;
   END getrolelist;

   ----------------------------------
   PROCEDURE getteamcategorybyid (vteamcategoryid   IN     NUMBER,
                                  io_cursor            OUT t_cursor,
                                  error_desc           OUT VARCHAR2)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
         SELECT teamcategoryid,
                teamcategoryname,
                rceflag,
                submissionqflag,
                springflag,
                fce_flag
           FROM dddca_teamcategory
          WHERE dddca_teamcategory.teamcategoryid = vteamcategoryid;

      io_cursor := v_cursor;
      error_desc := 'OK';
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         error_desc := 'NO RECORDS FOUND';
      WHEN OTHERS
      THEN
         error_desc := SQLERRM;
   END getteamcategorybyid;

   --Author: Amol Kashikar
   --Date:27/03/2006

   -- Purpose:
   --This procedure is used to Get all teams on the system.
   --Team Examples are Sales Geremany, Sales France ,etc.

   --Input Parameter:

   --Output Parameter:  Cursor: Teams
   --              Return value
   PROCEDURE getteams (vtype          IN     CHAR,
                       io_cursor         OUT t_cursor,
                       vreturnvalue      OUT VARCHAR2)
   IS
      v_cursor   t_cursor;
   BEGIN
      IF (vtype = 'A')
      THEN
         OPEN v_cursor FOR
              SELECT dddca_team.teamid,
                     dddca_team.teamcategoryid,
                     dddca_teamcategory.teamcategoryname,
                     dddca_team.team_name AS team
                FROM dddca_team, dddca_teamcategory
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND dddca_teamcategory.rceflag <> 'Y'
            ORDER BY team;

         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      ELSIF vtype = 'B'
      THEN
         OPEN v_cursor FOR
              SELECT DISTINCT
                     dddca_team.teamid,
                     dddca_team.team_name AS team,
                     dddca_teamcategory.springflag
                FROM dddca_team, dddca_teamcategory
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND (   dddca_teamcategory.submissionqflag = 'Y'
                          OR dddca_teamcategory.rceflag = 'Y')
                     AND (   dddca_teamcategory.springflag = 'N'
                          OR dddca_teamcategory.springflag IS NULL)
                     AND dddca_teamcategory.teamcategoryname NOT LIKE
                            '%System Admin%'
            ORDER BY dddca_team.teamid;

         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      ELSIF vtype = 'C'
      THEN                                       --Only Service Delivery Teams
         OPEN v_cursor FOR
              SELECT dddca_team.teamid,
                     dddca_team.teamcategoryid,
                     dddca_teamcategory.teamcategoryname,
                     dddca_team.team_name AS team
                FROM dddca_team, dddca_teamcategory
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND dddca_teamcategory.teamcategoryid = 2
                     AND dddca_teamcategory.rceflag <> 'Y'
            ORDER BY team;

         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      ELSIF vtype = 'D'
      THEN                                                    --Only RCE Teams
         OPEN v_cursor FOR
              SELECT dddca_team.teamid,
                     dddca_team.teamcategoryid,
                     dddca_teamcategory.teamcategoryname,
                     dddca_team.team_name AS team
                FROM dddca_team, dddca_teamcategory
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND (   dddca_teamcategory.rceflag = 'Y'
                          OR dddca_teamcategory.oet_flag = 'Y')
                     AND ---Pramod Mathew : New team OET is added :10th April 2007--------
                         (   dddca_teamcategory.springflag = 'N'
                          OR dddca_teamcategory.springflag IS NULL)
                     AND dddca_teamcategory.teamcategoryname NOT LIKE
                            '%System Admin%'
            ORDER BY team;

         /*       SELECT
                        DDDCA_TEAM.TEAMID,
                        DDDCA_TEAM.TEAMCATEGORYID,
                        DDDCA_TEAMCATEGORY.TEAMCATEGORYNAME,
                        DDDCA_TEAM.TEAM_NAME AS Team
                  FROM
                     DDDCA_TEAM,
                     DDDCA_TEAMCATEGORY
                  WHERE
                      DDDCA_TEAM.TEAMCATEGORYID = DDDCA_TEAMCATEGORY.TEAMCATEGORYID AND
                      DDDCA_TEAMCATEGORY.RCEFLAG = 'Y' AND
                      (DDDCA_TEAMCATEGORY.SPRINGFLAG = 'N' OR DDDCA_TEAMCATEGORY.SPRINGFLAG IS NULL )
                      AND
                      DDDCA_TEAMCATEGORY.TEAMCATEGORYNAME NOT LIKE '%System Admin%' --ORDER BY Team
                   UNION      ---Pramod Mathew : New team OET is added :10th April 2007--------
                      SELECT    DDDCA_TEAM.TEAMID,
                    DDDCA_TEAM.TEAMCATEGORYID,
                    DDDCA_TEAMCATEGORY.TEAMCATEGORYNAME,
                    DDDCA_TEAM.TEAM_NAME AS Team
                   FROM
                     DDDCA_TEAM,
                     DDDCA_TEAMCATEGORY
                   WHERE
                      DDDCA_TEAM.TEAMCATEGORYID = DDDCA_TEAMCATEGORY.TEAMCATEGORYID AND
                      DDDCA_TEAMCATEGORY.OET_FLAG = 'Y' AND
                      (DDDCA_TEAMCATEGORY.SPRINGFLAG = 'N' OR DDDCA_TEAMCATEGORY.SPRINGFLAG IS NULL )
                      AND
                      DDDCA_TEAMCATEGORY.TEAMCATEGORYNAME NOT LIKE '%System Admin%'; -- ORDER BY Team;*/
         ---Pramod Mathew : New team OET is added :10th April 2007--------
         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      ELSIF vtype = 'M'
      THEN
         OPEN v_cursor FOR
              SELECT DISTINCT dddca_team.teamid,
                              dddca_team.teamcategoryid,
                              dddca_teamcategory.teamcategoryname,
                              dddca_team.team_name AS team
                FROM dddca_team,
                     dddca_teamcategory,
                     dddca_team_product,
                     dddca_product,
                     dddca_product_category
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND dddca_team.teamid = dddca_team_product.team_id
                     AND dddca_team_product.product_id =
                            dddca_product.productid
                     AND dddca_product.productcategoryid =
                            dddca_product_category.prodcategoryid
                     AND dddca_product_category.productcategoryname = 'MPLS'
                     AND dddca_teamcategory.teamcategoryname NOT LIKE
                            '%System Admin%'
            ORDER BY team;

         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      ELSIF vtype = 'S'
      THEN
         OPEN v_cursor FOR
              SELECT DISTINCT dddca_team.teamid,
                              dddca_team.teamcategoryid,
                              dddca_teamcategory.teamcategoryname,
                              dddca_team.team_name AS team
                FROM dddca_team,
                     dddca_teamcategory,
                     dddca_team_product,
                     dddca_product,
                     dddca_product_category
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND dddca_team.teamid = dddca_team_product.team_id
                     AND dddca_team_product.product_id =
                            dddca_product.productid
                     AND dddca_product.productcategoryid =
                            dddca_product_category.prodcategoryid
                     AND dddca_product_category.productcategoryname = 'SPRING'
                     AND dddca_teamcategory.teamcategoryname NOT LIKE
                            '%System Admin%'
            ORDER BY team;

         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      ELSIF vtype = 'L'
      THEN
         OPEN v_cursor FOR
              SELECT dddca_team.teamid,
                     dddca_team.teamcategoryid,
                     dddca_teamcategory.teamcategoryname,
                     dddca_team.team_name AS team
                FROM dddca_team, dddca_teamcategory
               WHERE     dddca_team.teamcategoryid =
                            dddca_teamcategory.teamcategoryid
                     AND dddca_teamcategory.teamcategoryname NOT LIKE
                            '%System Admin%'
            ORDER BY team;

         io_cursor := v_cursor;
         vreturnvalue := 'OK';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         vreturnvalue := 'NO DATA';
      WHEN OTHERS
      THEN
         vreturnvalue := 'ERROR';
   END getteams;

   --Author: Amol Kashikar
   --Date:27/03/2006

   -- Purpose:

   --Input Parameter:

   --Output Parameter:
   PROCEDURE addupdatesubmissionqueue (nteamid               IN     NUMBER,
                                       nproductid            IN     NUMBER,
                                       nqueueid              IN     NUMBER,
                                       vemail                IN     VARCHAR2,
                                       ndefaultowner         IN     NUMBER,
                                       nrejectteam           IN     NUMBER,
                                       nrejectdefaultowner   IN     NUMBER,
                                       vreturnvalue             OUT VARCHAR2)
   AS
      vcount   NUMBER;
   BEGIN
      SELECT COUNT (dddca_submission_queue.submissionqid)
        INTO vcount
        FROM dddca_submission_queue
       WHERE     dddca_submission_queue.teamid = nteamid
             AND dddca_submission_queue.productid = nproductid;

      IF (vcount > 0)
      THEN
         IF nrejectteam = 0
         THEN
            UPDATE dddca_submission_queue
               SET queueid = nqueueid,
                   email = vemail,
                   defaultowner = ndefaultowner,
                   rejectqueueid = NULL,
                   rejectdefaultowner = NULL
             WHERE teamid = nteamid AND productid = nproductid;
         ELSE
            UPDATE dddca_submission_queue
               SET queueid = nqueueid,
                   email = vemail,
                   defaultowner = ndefaultowner,
                   rejectqueueid = nrejectteam,
                   rejectdefaultowner = nrejectdefaultowner
             WHERE teamid = nteamid AND productid = nproductid;
         END IF;

         vreturnvalue := 'OK';
         COMMIT;
      ELSE
         IF nrejectteam = 0
         THEN
            INSERT INTO dddca_submission_queue (teamid,
                                                productid,
                                                queueid,
                                                email,
                                                defaultowner)
                 VALUES (nteamid,
                         nproductid,
                         nqueueid,
                         vemail,
                         ndefaultowner);
         ELSE
            INSERT INTO dddca_submission_queue (teamid,
                                                productid,
                                                queueid,
                                                email,
                                                defaultowner,
                                                rejectqueueid,
                                                rejectdefaultowner)
                 VALUES (nteamid,
                         nproductid,
                         nqueueid,
                         vemail,
                         ndefaultowner,
                         nrejectteam,
                         nrejectdefaultowner);
         END IF;

         vreturnvalue := 'OK';
         COMMIT;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         vreturnvalue := 'ERROR';
   END addupdatesubmissionqueue;

   --Author: Amol Kashikar
   --Date:27/03/2006

   -- Purpose:
   --This procedure is used to Get all Products on the system.
   --Product Examples are BT MPLS- STD DSL, BT MPLS XDSL, Spring.

   --Input Parameter:
   --Output Parameter:  Cursor: Teams.
   --              Return value.
   PROCEDURE getallproducts (io_cursor      OUT t_cursor,
                             vreturnvalue   OUT VARCHAR2)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT productid, productname
             FROM dddca_product
            WHERE productname NOT LIKE '%SP%'
         ORDER BY productid;

      io_cursor := v_cursor;
      vreturnvalue := 'OK';
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         vreturnvalue := 'NO DATA';
      WHEN OTHERS
      THEN
         vreturnvalue := 'ERROR';
   END getallproducts;

   -------------------------------
   --Author: Amol Kashikar
   --Date:30/03/2006

   -- Purpose:
   --This procedure is used to Get Submission Queue.

   --Output Parameter:  Cursor: Team Category, Errro Description
   PROCEDURE getsubmissionqueue (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT DISTINCT
                  dddca_submission_queue.submissionqid,
                  dddca_submission_queue.teamid,
                  CASE
                     WHEN dddca_teamcategory.rceflag = 'Y'
                     THEN
                        dddca_teamcategory.teamcategoryname
                     ELSE
                           dddca_teamcategory.teamcategoryname
                        || ' '
                        || dddca_teamcountries.countryname
                  END
                     AS team,
                  dddca_product.productname,
                  dddca_submission_queue.queueid,
                  dddca_pk_user_pro.teamname (dddca_submission_queue.queueid)
                     AS submissionqueue,
                  dddca_submission_queue.email,
                  dddca_model.modelname
             FROM dddca_team,
                  dddca_submission_queue,
                  dddca_product,
                  dddca_teamcategory,
                  dddca_teamcountries,
                  dddca_model,
                  dddca_prod_cont_model
            WHERE     dddca_model.modelid = dddca_prod_cont_model.modelid
                  AND dddca_prod_cont_model.productid =
                         dddca_submission_queue.productid
                  AND dddca_prod_cont_model.country =
                         dddca_teamcountries.countryname
                  AND dddca_team.teamid = dddca_submission_queue.teamid
                  AND dddca_submission_queue.productid =
                         dddca_product.productid
                  AND dddca_team.teamcategoryid =
                         dddca_teamcategory.teamcategoryid
                  AND dddca_teamcountries.teamid = dddca_team.teamid
                  AND dddca_product.productid <> 3
                  AND dddca_team.teamid NOT IN (SELECT teamid
                                                  FROM dddca_team
                                                 WHERE teamcategoryid IN (SELECT teamcategoryid
                                                                            FROM dddca_teamcategory
                                                                           WHERE    rceflag =
                                                                                       'Y'
                                                                                 OR springflag =
                                                                                       'Y'))
         --Added by Rajul to not display the SPRING product in submission queue
         ORDER BY team, productname;

      io_cursor := v_cursor;
   END getsubmissionqueue;

   -------------------------------
   --Author: Rajul Patel
   --Date:31/03/2006

   -- Purpose:
   --This procedure is used to Get Submission Queue Detail.
   --InPut Parameter : Submission Queue Id
   --Output Parameter:  Cursor: Submission Queue Details
   PROCEDURE getsubmissionqueuedetail (vsubmissionqid   IN     NUMBER,
                                       io_cursor           OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
         SELECT dddca_submission_queue.submissionqid,
                dddca_submission_queue.productid,
                dddca_submission_queue.teamid,
                dddca_submission_queue.queueid,
                dddca_submission_queue.email,
                dddca_submission_queue.defaultowner,
                dddca_submission_queue.rejectqueueid,
                dddca_submission_queue.rejectdefaultowner
           FROM dddca_submission_queue
          WHERE dddca_submission_queue.submissionqid = vsubmissionqid;

      io_cursor := v_cursor;
   END getsubmissionqueuedetail;

   -------------------------------
   --Author: Amol Kashikar
   --Date:06/04/2006

   -- Purpose:
   --This procedure is used to get orders from Online DCA for a particular user.

   --InPut Parameter : Userid, Type,
   --Output Parameter:  Cursor: Order details.
   -------------------------------
   --Author: Amol Kashikar
   --Date:06/04/2006

   -- Purpose:
   --This procedure is used to get orders from Online DCA for a particular user.

   --InPut Parameter : Userid, Type,
   --Output Parameter:  Cursor: Order details.
   PROCEDURE getorders (nuserid           IN     NUMBER,
                        nteamid           IN     NUMBER,
                        vproductname      IN     VARCHAR2,
                        vtype             IN     VARCHAR,
                        nteamcategoryid   IN OUT NUMBER,
                        vort_flag         IN     VARCHAR,
                        NPROFILEID        IN     NUMBER,
                        V_PG_START        IN     NUMBER,
                        v_pg_end          IN     NUMBER,
                        io_cursor            OUT t_cursor,      
                        VRETURNVALUE         OUT VARCHAR2)
   IS
      v_cursor          t_cursor;                               
      vcountry          VARCHAR2 (100);
      icount            NUMBER;
      newteamcategory   NUMBER;
   BEGIN
      newteamcategory := nteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      SELECT COUNT (*)
        INTO icount                           -----gousiya -- 26th april 2007;
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.fce_flag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 7;
      END IF;

      nteamcategoryid := newteamcategory;

      /*************************************************/
      --Getting Pending Orders
      /*************************************************/
      IF UPPER (vtype) = 'P'
      THEN
         BEGIN
            IF vproductname = 'MPLS'
            THEN
               /*************************************************/
               --Getting Pending Orders for MPLS
               /*************************************************/
               BEGIN
                  --Sales
                  IF (nteamcategoryid = 1)
                  THEN
                     OPEN V_CURSOR FOR
                        SELECT *
                          FROM (SELECT order_id,
                                       customername,
                                       created_date,
                                       ORDERSTATUS,
                                       customer_id,
                                       menu_flag,
                                       MENU_NAME,
                                       ROW_NUMBER ()
                                          OVER (ORDER BY order_id DESC)
                                          rn,
                                       COUNT (*) OVER () cnt
                                  FROM (SELECT dddca_order_details.order_id,
                                               dddca_customer.customername,
                                               dddca_order_details.created_date,
                                               dddca_order_details.orderstatus,
                                               dddca_customer.mastercustomerid
                                                  AS customer_id,
                                               vtype AS menu_flag,
                                               (SELECT menuname
                                                  FROM dddca_menu
                                                 WHERE menuurl =
                                                             'OrderList.aspx?Flag='
                                                          || vtype)
                                                  AS menu_name
                                          FROM dddca_order_details,
                                               dddca_customer
                                         WHERE     dddca_order_details.order_id =
                                                      dddca_customer.order_id(+)
                                               AND (   (    dddca_order_details.teamid =
                                                               nteamid
                                                        AND dddca_order_details.delegatedtoteamid
                                                               IS NULL)
                                                    OR dddca_order_details.delegatedtoteamid =
                                                          nteamid)
                                               AND (   dddca_order_details.orderstatus IN ('SALES IN PROGRESS')
                                                    OR (    dddca_order_details.orderstatus IN ('REJECTED TO SALES',
                                                                                                'CANCELED BY SD')
                                                        AND (   dddca_order_details.creator_person_id <>
                                                                   nuserid
                                                             OR dddca_order_details.delegatedto =
                                                                   nuserid)))
                                               AND (   (dddca_order_details.delegatedto =
                                                           nuserid)
                                                    OR (    dddca_order_details.creator_person_id =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto
                                                               IS NULL)
                                                    OR (    dddca_order_details.creator_person_id =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto =
                                                               nuserid))
                                               AND dddca_order_details.ordertype =
                                                      vproductname
                                               AND (   dddca_customer.customertype =
                                                          'I'
                                                    OR dddca_customer.customertype
                                                          IS NULL)
                                               AND UPPER (
                                                      dddca_order_details.ort_flag) =
                                                      UPPER (vort_flag)
                                               AND dddca_customer.customername
                                                      IS NOT NULL
                                        --ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC
                                        UNION
                                        SELECT DISTINCT
                                               dddca_order_details.order_id,
                                               dddca_customer_modcease.customername,
                                               dddca_order_details.created_date,
                                               dddca_order_details.orderstatus,
                                               dddca_customer_modcease.customerid
                                                  AS customer_id,
                                               vtype AS menu_flag,
                                               (SELECT menuname
                                                  FROM dddca_menu
                                                 WHERE menuurl =
                                                             'OrderList.aspx?Flag='
                                                          || vtype)
                                                  AS menu_name
                                          FROM dddca_order_details,
                                               dddca_customer_modcease
                                         WHERE     dddca_order_details.order_id =
                                                      dddca_customer_modcease.orderid(+)
                                               AND (   (    dddca_order_details.teamid =
                                                               nteamid
                                                        AND dddca_order_details.delegatedtoteamid
                                                               IS NULL)
                                                    OR dddca_order_details.delegatedtoteamid =
                                                          nteamid)
                                               AND (   dddca_order_details.orderstatus IN ('SALES IN PROGRESS')
                                                    OR (    dddca_order_details.orderstatus IN ('REJECTED TO SALES',
                                                                                                'CANCELED BY SD')
                                                        AND (   dddca_order_details.creator_person_id <>
                                                                   nuserid
                                                             OR dddca_order_details.delegatedto =
                                                                   nuserid)))
                                               AND (   (dddca_order_details.delegatedto =
                                                           nuserid)
                                                    OR (    dddca_order_details.creator_person_id =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto
                                                               IS NULL)
                                                    OR (    dddca_order_details.creator_person_id =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto =
                                                               nuserid))
                                               AND dddca_order_details.ordertype =
                                                      vproductname
                                               --AND (DDDCA_CUSTOMER_MODCEASE.CUSTOMERTYPE = 'I' OR DDDCA_CUSTOMER_MODCEASE.CUSTOMERTYPE IS NULL)
                                               AND UPPER (
                                                      dddca_order_details.ort_flag) =
                                                      UPPER (vort_flag)
                                               AND DDDCA_CUSTOMER_MODCEASE.CUSTOMERNAME
                                                      IS NOT NULL
                                        ORDER BY 1 DESC))
                         WHERE rn BETWEEN v_pg_start AND v_pg_end;

                     io_cursor := v_cursor;
                     VRETURNVALUE := 'OK';
                  --SD
                  ELSIF (nteamcategoryid = 2)
                  THEN
                     OPEN V_CURSOR FOR
                        SELECT *
                          FROM (SELECT order_id,
                                       customername,
                                       created_date,
                                       ORDERSTATUS,
                                       customer_id,
                                       menu_flag,
                                       MENU_NAME,
                                       ROW_NUMBER ()
                                          OVER (ORDER BY order_id DESC)
                                          rn,
                                       COUNT (*) OVER () cnt
                                  FROM (SELECT dddca_order_details.order_id,
                                               dddca_customer.customername,
                                               dddca_order_details.created_date,
                                               dddca_order_details.orderstatus,
                                               dddca_customer.mastercustomerid
                                                  AS customer_id,
                                               vtype AS menu_flag,
                                               (SELECT menuname
                                                  FROM dddca_menu
                                                 WHERE menuurl =
                                                             'OrderList.aspx?Flag='
                                                          || vtype)
                                                  AS menu_name
                                          FROM dddca_order_details,
                                               dddca_customer
                                         WHERE     dddca_order_details.order_id =
                                                      dddca_customer.order_id(+)
                                               AND --(dddca_order_details.ORDEROWNER = nUserid or dddca_order_details.DELEGATEDTO = nUserid) and
                                                   (   (dddca_order_details.delegatedto =
                                                           nuserid)
                                                    OR (    dddca_order_details.orderowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto
                                                               IS NULL)
                                                    OR (    dddca_order_details.orderowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto =
                                                               nuserid))
                                               AND (   (    dddca_order_details.teamid =
                                                               nteamid
                                                        AND dddca_order_details.delegatedtoteamid
                                                               IS NULL)
                                                    OR dddca_order_details.delegatedtoteamid =
                                                          nteamid)
                                               /*
                                               (
                                               dddca_order_details.TEAMID = nTeamId or
                                               dddca_order_details.delegatedtoteamid = nTeamId or
                                               dddca_order_details.delegatedtoteamid is null
                                               ) */
                                               AND (   dddca_order_details.orderstatus IN ('SUBMIT TO SD',
                                                                                           'ORDER SUBMISSION ERROR')
                                                    OR (    dddca_order_details.orderstatus IN ('REJECTED TO SD',
                                                                                                'ORDER SUBMISSION ERROR')
                                                        AND (   dddca_order_details.orderowner <>
                                                                   nuserid
                                                             OR dddca_order_details.delegatedto =
                                                                   nuserid)))
                                               AND dddca_order_details.ordertype =
                                                      vproductname
                                               AND (   dddca_customer.customertype =
                                                          'I'
                                                    OR dddca_customer.customertype
                                                          IS NULL)
                                               AND UPPER (
                                                      dddca_order_details.ort_flag) =
                                                      UPPER (vort_flag)
                                               AND dddca_customer.customername
                                                      IS NOT NULL
                                        --ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC
                                        UNION
                                        SELECT DISTINCT
                                               dddca_order_details.order_id,
                                               dddca_customer_modcease.customername,
                                               dddca_order_details.created_date,
                                               dddca_order_details.orderstatus,
                                               dddca_customer_modcease.customerid
                                                  AS customer_id,
                                               vtype AS menu_flag,
                                               (SELECT menuname
                                                  FROM dddca_menu
                                                 WHERE menuurl =
                                                             'OrderList.aspx?Flag='
                                                          || vtype)
                                                  AS menu_name
                                          FROM dddca_order_details,
                                               dddca_customer_modcease
                                         WHERE     dddca_order_details.order_id =
                                                      dddca_customer_modcease.orderid(+)
                                               AND --(dddca_order_details.ORDEROWNER = nUserid or dddca_order_details.DELEGATEDTO = nUserid) and
                                                   (   (dddca_order_details.delegatedto =
                                                           nuserid)
                                                    OR (    dddca_order_details.orderowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto
                                                               IS NULL)
                                                    OR (    dddca_order_details.orderowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto =
                                                               nuserid))
                                               AND (   (    dddca_order_details.teamid =
                                                               nteamid
                                                        AND dddca_order_details.delegatedtoteamid
                                                               IS NULL)
                                                    OR dddca_order_details.delegatedtoteamid =
                                                          nteamid)
                                               /*
                                               (
                                               dddca_order_details.TEAMID = nTeamId or
                                               dddca_order_details.delegatedtoteamid = nTeamId or
                                               dddca_order_details.delegatedtoteamid is null
                                               ) */
                                               AND (   dddca_order_details.orderstatus IN ('SUBMIT TO SD',
                                                                                           'ORDER SUBMISSION ERROR')
                                                    OR (    dddca_order_details.orderstatus IN ('REJECTED TO SD',
                                                                                                'ORDER SUBMISSION ERROR')
                                                        AND (   dddca_order_details.orderowner <>
                                                                   nuserid
                                                             OR dddca_order_details.delegatedto =
                                                                   nuserid)))
                                               AND dddca_order_details.ordertype =
                                                      vproductname
                                               --AND (DDDCA_CUSTOMER_MODCEASE.CUSTOMERTYPE = 'I' OR DDDCA_CUSTOMER_MODCEASE.CUSTOMERTYPE  IS NULL)
                                               AND UPPER (
                                                      dddca_order_details.ort_flag) =
                                                      UPPER (vort_flag)
                                               AND DDDCA_CUSTOMER_MODCEASE.CUSTOMERNAME
                                                      IS NOT NULL
                                        ORDER BY 1 DESC))
                         WHERE rn BETWEEN v_pg_start AND v_pg_end;

                     io_cursor := v_cursor;
                     vreturnvalue := 'OK';
                  --RCE PUNE AND BUDAPEST
                  --ELSIF (nteamcategoryid = 5) OR (nteamcategoryid = 4) OR (nteamcategoryid = 6) THEN
                  ELSIF nteamcategoryid IN (4, 5, 6)
                  THEN
                     ----Pramod Mathew : New team OET is added :10th April 2007---
                     OPEN V_CURSOR FOR
                        SELECT *
                          FROM (SELECT order_id,
                                       customername,
                                       created_date,
                                       ORDERSTATUS,
                                       customer_id,
                                       menu_flag,
                                       MENU_NAME,
                                       ROW_NUMBER ()
                                          OVER (ORDER BY order_id DESC)
                                          rn,
                                       COUNT (*) OVER () cnt
                                  FROM (SELECT dddca_order_details.order_id,
                                               dddca_customer.customername,
                                               dddca_order_details.created_date,
                                               dddca_order_details.orderstatus,
                                               dddca_customer.mastercustomerid
                                                  AS customer_id,
                                               vtype AS menu_flag,
                                               (SELECT menuname
                                                  FROM dddca_menu
                                                 WHERE menuurl =
                                                             'OrderList.aspx?Flag='
                                                          || vtype)
                                                  AS menu_name
                                          FROM dddca_order_details,
                                               dddca_customer
                                         WHERE     dddca_order_details.order_id =
                                                      dddca_customer.order_id(+)
                                               AND (   (dddca_order_details.delegatedto =
                                                           nuserid)
                                                    OR (    dddca_order_details.rceowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto
                                                               IS NULL)
                                                    OR (    dddca_order_details.rceowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto =
                                                               nuserid))
                                               AND (   (    dddca_order_details.teamid =
                                                               nteamid
                                                        AND dddca_order_details.delegatedtoteamid
                                                               IS NULL)
                                                    OR dddca_order_details.delegatedtoteamid =
                                                          nteamid)
                                               AND /*
                                                   (
                                                   dddca_order_details.TEAMID = nTeamId or
                                                   dddca_order_details.delegatedtoteamid = nTeamId or
                                                   dddca_order_details.delegatedtoteamid is null
                                                   ) and*/
                                                   dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                       'ORDER SUBMISSION ERROR',
                                                                                       'SUBMIT TO OET')
                                               AND dddca_order_details.ordertype =
                                                      vproductname
                                               AND (   dddca_customer.customertype =
                                                          'I'
                                                    OR dddca_customer.customertype
                                                          IS NULL)
                                               AND UPPER (
                                                      dddca_order_details.ort_flag) =
                                                      UPPER (vort_flag)
                                               AND dddca_customer.customername
                                                      IS NOT NULL
                                        --ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC
                                        UNION
                                        SELECT dddca_order_details.order_id,
                                               dddca_customer_modcease.customername,
                                               dddca_order_details.created_date,
                                               dddca_order_details.orderstatus,
                                               dddca_customer_modcease.customerid
                                                  AS customer_id,
                                               vtype AS menu_flag,
                                               (SELECT menuname
                                                  FROM dddca_menu
                                                 WHERE menuurl =
                                                             'OrderList.aspx?Flag='
                                                          || vtype)
                                                  AS menu_name
                                          FROM dddca_order_details,
                                               dddca_customer_modcease
                                         WHERE     dddca_order_details.order_id =
                                                      dddca_customer_modcease.orderid(+)
                                               AND (   (dddca_order_details.delegatedto =
                                                           nuserid)
                                                    OR (    dddca_order_details.rceowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto
                                                               IS NULL)
                                                    OR (    dddca_order_details.rceowner =
                                                               nuserid
                                                        AND dddca_order_details.delegatedto =
                                                               nuserid))
                                               AND (   (    dddca_order_details.teamid =
                                                               nteamid
                                                        AND dddca_order_details.delegatedtoteamid
                                                               IS NULL)
                                                    OR dddca_order_details.delegatedtoteamid =
                                                          nteamid)
                                               AND /*
                                                   (
                                                   dddca_order_details.TEAMID = nTeamId or
                                                   dddca_order_details.delegatedtoteamid = nTeamId or
                                                   dddca_order_details.delegatedtoteamid is null
                                                   ) and*/
                                                   dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                       'ORDER SUBMISSION ERROR',
                                                                                       'SUBMIT TO OET')
                                               AND dddca_order_details.ordertype =
                                                      vproductname
                                               --AND (DDDCA_CUSTOMER_MODCEASE.CUSTOMERTYPE = 'I' OR DDDCA_CUSTOMER_MODCEASE.CUSTOMERTYPE  IS NULL)
                                               AND UPPER (
                                                      dddca_order_details.ort_flag) =
                                                      UPPER (vort_flag)
                                               AND DDDCA_CUSTOMER_MODCEASE.CUSTOMERNAME
                                                      IS NOT NULL
                                        ORDER BY 1 DESC))
                         WHERE rn BETWEEN v_pg_start AND v_pg_end;

                     io_cursor := v_cursor;
                     vreturnvalue := 'OK';
                  END IF;
               END;
            ELSIF vproductname = 'SPRING'
            THEN
               /*************************************************/
               --Getting Pending Orders for Spring
               /*************************************************/
               BEGIN
                  OPEN V_CURSOR FOR
                     SELECT *
                       FROM (SELECT order_id,
                                    order_reference,
                                    client_account_id,
                                    Client_Name,
                                    received_date,
                                    menu_flag,
                                    menu_name,
                                    sort_id,
                                    BACK_COLOR,
                                    ROW_NUMBER ()
                                       OVER (ORDER BY order_id DESC)
                                       rn,
                                    COUNT (*) OVER () cnt
                               FROM (  SELECT DISTINCT
                                              order_id,
                                              dd_dca_pk.concat_orderreference (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS order_reference,
                                              --ORDER_REFERENCE, Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                              --CLIENT_ACCOUNT_ID,
                                              dd_dca_pk.concat_accountid (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_account_id,
                                              --CLIENT_NAME AS "Client Name",
                                              dddca_pk_user_profile.concat_clientname (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS Client_Name,
                                              TO_CHAR (order_received_date,
                                                       'DD-MON-YYYY')
                                                 received_date,
                                              vtype AS menu_flag,
                                              (SELECT menuname
                                                 FROM dddca_menu
                                                WHERE menuurl =
                                                            'OrderList.aspx?Flag='
                                                         || vtype)
                                                 AS menu_name,
                                              sort_id,
                                              dddca_pk_user_profile.get_backcolor_ordertype (
                                                 dddca_order_details.order_id)
                                                 AS back_color
                                         FROM dddca_order_details,
                                              dddca_reuters_client drc
                                        WHERE     dddca_order_details.order_id =
                                                     drc.orderid(+)
                                              --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                              --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                              --Show "Provide" order as before (MOD_STATUS IS NULL).
                                              AND (   dddca_order_details.mod_status =
                                                         'Y'
                                                   OR dddca_order_details.mod_status
                                                         IS NULL)
                                              --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                       'SCENARIO2',
                                                                                                       'SCENARIO4',
                                                                                                       'SCENARIO5')
                                                   OR dddca_order_details.inflight_scenario_id
                                                         IS NULL)
                                              AND (   (dddca_order_details.delegatedto =
                                                          nuserid)
                                                   OR (    dddca_order_details.orderowner =
                                                              nuserid
                                                       AND dddca_order_details.delegatedto
                                                              IS NULL)
                                                   OR (    dddca_order_details.orderowner =
                                                              nuserid
                                                       AND dddca_order_details.delegatedto =
                                                              nuserid))
                                              AND --(dddca_order_details.DELEGATEDTO = nUserid
                                                  -- or dddca_order_details.ORDEROWNER = nUserid) and
                                                  (   (    dddca_order_details.teamid =
                                                              nteamid
                                                       AND dddca_order_details.delegatedtoteamid
                                                              IS NULL)
                                                   OR dddca_order_details.delegatedtoteamid =
                                                         nteamid)
                                              /*
                                              (
                                              dddca_order_details.TEAMID = nTeamId or
                                              dddca_order_details.delegatedtoteamid = nTeamId or
                                              dddca_order_details.delegatedtoteamid is null
                                              ) */
                                              AND dddca_order_details.teamid =
                                                     nteamid
                                              AND dddca_order_details.creator_person_id
                                                     IS NULL
                                              AND (    UPPER (
                                                          dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                               'ORDER SUBMISSION ERROR')
                                                   AND UPPER (
                                                          dddca_order_details.ordertype) =
                                                          UPPER (vproductname)
                                                   AND (   pass1status <> 'T'
                                                        OR pass1status IS NULL))
                                              AND UPPER (
                                                     dddca_order_details.ort_flag) =
                                                     UPPER (vort_flag)
                                              --Commented for R22_GSCE_85005_eDCA_Delegation by Kanakshree on 09/07/29012
                                              -- AND (   dddca_order_details.delegatereason <>
                                              -- 'AAF Approval Required'
                                              AND (   dddca_order_details.delegatereason NOT IN ('More Info Required',
                                                                                                 'Rejected',
                                                                                                 'Approved')
                                                   OR dddca_order_details.delegatereason
                                                         IS NULL)
                                     ORDER BY sort_id, order_id ASC))
                      WHERE rn BETWEEN v_pg_start AND v_pg_end;

                  --ORDER BY ORDER_ID DESC;
                  --End Change ---Upendra---------------------------------------------------
                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            --END IF;
            ELSIF vproductname = 'FCE'
            THEN
               --------gousiya -- added the fce pending orders -26th april 2007
               /*************************************************/
               --Getting Pending Orders for FCE
               /*************************************************/
               BEGIN
                  OPEN V_CURSOR FOR
                     SELECT *
                       FROM (SELECT order_id,
                                    order_reference,
                                    client_account_id,
                                    client_name,
                                    received_date,
                                    MENU_FLAG,
                                    MENU_NAME,
                                    ROW_NUMBER ()
                                       OVER (ORDER BY order_id DESC)
                                       rn,
                                    COUNT (*) OVER () cnt
                               FROM (  SELECT order_id,
                                              order_reference,
                                              client_account_id,
                                              client_name,
                                              TO_CHAR (order_received_date,
                                                       'DD-MON-YYYY')
                                                 received_date,
                                              vtype AS menu_flag,
                                              (SELECT menuname
                                                 FROM dddca_menu
                                                WHERE menuurl =
                                                            'OrderList.aspx?Flag='
                                                         || vtype)
                                                 AS menu_name
                                         FROM dddca_order_details,
                                              dddca_reuters_client drc
                                        WHERE     dddca_order_details.order_id =
                                                     drc.orderid
                                              --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                              --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                              --Show "Provide" order as before (MOD_STATUS IS NULL).
                                              AND (   dddca_order_details.mod_status =
                                                         'Y'
                                                   OR dddca_order_details.mod_status
                                                         IS NULL)
                                              --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                       'SCENARIO2',
                                                                                                       'SCENARIO4',
                                                                                                       'SCENARIO5')
                                                   OR dddca_order_details.inflight_scenario_id
                                                         IS NULL)
                                              AND (   (dddca_order_details.delegatedto =
                                                          nuserid)
                                                   OR (    dddca_order_details.orderowner =
                                                              nuserid
                                                       AND dddca_order_details.delegatedto
                                                              IS NULL)
                                                   OR (    dddca_order_details.orderowner =
                                                              nuserid
                                                       AND dddca_order_details.delegatedto =
                                                              nuserid))
                                              AND --(dddca_order_details.DELEGATEDTO = nUserid
                                                  -- or dddca_order_details.ORDEROWNER = nUserid) and
                                                  (   (    dddca_order_details.teamid =
                                                              nteamid
                                                       AND dddca_order_details.delegatedtoteamid
                                                              IS NULL)
                                                   OR dddca_order_details.delegatedtoteamid =
                                                         nteamid)
                                              /*
                                              (
                                              dddca_order_details.TEAMID = nTeamId or
                                              dddca_order_details.delegatedtoteamid = nTeamId or
                                              dddca_order_details.delegatedtoteamid is null
                                              ) */
                                              AND dddca_order_details.teamid =
                                                     nteamid
                                              AND dddca_order_details.creator_person_id
                                                     IS NULL
                                              AND (    UPPER (
                                                          dddca_order_details.orderstatus) IN ('ORIGINATED BY FCE',
                                                                                               'ORDER SUBMISSION ERROR')
                                                   AND UPPER (
                                                          dddca_order_details.ordertype) =
                                                          UPPER (vproductname)
                                                   AND (   pass1status <> 'T'
                                                        OR pass1status IS NULL))
                                              AND UPPER (
                                                     dddca_order_details.ort_flag) =
                                                     UPPER (VORT_FLAG)
                                     ORDER BY ORDER_ID DESC))
                      WHERE rn BETWEEN v_pg_start AND v_pg_end;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      /*************************************************/
      --END  OF Pending Orders
      /*************************************************/
      /*************************************************/
      --Getting Submitted Orders for MPLS
      /*************************************************/
      ELSIF UPPER (vtype) = 'SSD'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Submitted Orders for Sales.
            /*************************************************/
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               /*    OPEN v_cursor FOR
               select dddca_order_details.ORDER_ID,
               dddca_customer.CUSTOMERNAME,
               dddca_order_details.CREATED_DATE,
               dddca_order_details.ORDERSTATUS
               from  dddca_order_details, dddca_customer
               where
               dddca_order_details.ORDER_ID =   dddca_customer.ORDER_ID and
               (dddca_order_details.CREATOR_PERSON_ID = nUserid) and
               dddca_order_details.TEAMID = nTeamId and
               (dddca_order_details.ORDERSTATUS = 'SUBMIT TO SD') and
               dddca_order_details.ORDERTYPE =  vProductName and dddca_customer.CUSTOMERTYPE = 'I';
               */
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.creator_person_id =
                                                   nuserid)
                                           AND dddca_order_details.teamid <>
                                                  nteamid
                                           AND dddca_order_details.salesteam =
                                                  nteamid
                                           AND (   (    dddca_order_details.orderstatus IN ('SUBMIT TO SD',
                                                                                            'ORDER SUBMISSION ERROR',
                                                                                            'REQUEST CREATED',
                                                                                            'UPLOAD PASS1 TO CLASSIC',
                                                                                            'UPLOAD PASS2 TO CLASSIC')
                                                    AND dddca_order_details.orderowner
                                                           IS NOT NULL)
                                                OR (    dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                            'REJECTED TO SD',
                                                                                            'SUBMIT TO OET')
                                                    --,'ORDER SUBMISSION ERROR','REQUEST CREATED')
                                                    --Pramod Mathew : New team OET is added :10th April 2007---
                                                    AND dddca_order_details.sdteam
                                                           IS NOT NULL))
                                           AND dddca_order_details.ordertype =
                                                  vproductname
                                           AND (   dddca_customer.customertype =
                                                      'I'
                                                OR dddca_customer.customertype
                                                      IS NULL)
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (vort_flag)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --Service delivery
            /*elsif(nTeamCategoryId = 2) then
            OPEN v_cursor FOR
            select dddca_order_details.ORDER_ID,
            dddca_customer.CUSTOMERNAME,
            dddca_order_details.CREATED_DATE,
            dddca_order_details.ORDERSTATUS
            from  dddca_order_details, dddca_customer
            where
            dddca_order_details.ORDER_ID =   dddca_customer.ORDER_ID and
            (dddca_order_details.ORDEROWNER = nUserid) and
            (dddca_order_details.ORDERSTATUS = 'SUBMIT TO RCE') and
            dddca_order_details.ORDERTYPE =  vProductName and dddca_customer.CUSTOMERTYPE = 'I';
            io_cursor := v_cursor;
            vReturnValue:='OK';*/
            END IF;
         END IF;
      ELSIF UPPER (vtype) = 'SRCE'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Submitted Orders for Sales.
            /*************************************************/
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               /*    OPEN v_cursor FOR
               select dddca_order_details.ORDER_ID,
               dddca_customer.CUSTOMERNAME,
               dddca_order_details.CREATED_DATE,
               dddca_order_details.ORDERSTATUS
               from  dddca_order_details, dddca_customer
               where
               dddca_order_details.ORDER_ID =   dddca_customer.ORDER_ID and
               (dddca_order_details.CREATOR_PERSON_ID = nUserid) and
               dddca_order_details.TEAMID = nTeamId and
               (dddca_order_details.ORDERSTATUS = 'SUBMIT TO SD') and
               dddca_order_details.ORDERTYPE =  vProductName and dddca_customer.CUSTOMERTYPE = 'I';
               */
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.creator_person_id =
                                                   nuserid)
                                           AND dddca_order_details.teamid <>
                                                  nteamid
                                           AND dddca_order_details.salesteam =
                                                  nteamid
                                           AND (   (    dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                            'ORDER SUBMISSION ERROR',
                                                                                            'REQUEST CREATED',
                                                                                            'UPLOAD PASS1 TO CLASSIC',
                                                                                            'UPLOAD PASS2 TO CLASSIC',
                                                                                            'SUBMIT TO OET')
                                                    --Pramod Mathew : New team OET is added :10th April 2007---
                                                    AND dddca_order_details.orderowner
                                                           IS NULL)
                                                OR (    dddca_order_details.orderstatus IN ('REJECTED TO SD')
                                                    AND dddca_order_details.sdteam
                                                           IS NULL))
                                           AND dddca_order_details.sdteam
                                                  IS NULL
                                           AND dddca_order_details.ordertype =
                                                  vproductname
                                           AND (   dddca_customer.customertype =
                                                      'I'
                                                OR dddca_customer.customertype
                                                      IS NULL)
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --Service delivery
            ELSIF (nteamcategoryid = 2)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.orderowner =
                                                   nuserid)
                                           AND (dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                    'ORDER SUBMISSION ERROR',
                                                                                    'REQUEST CREATED',
                                                                                    'UPLOAD PASS1 TO CLASSIC',
                                                                                    'UPLOAD PASS2 TO CLASSIC',
                                                                                    'SUBMIT TO OET'))
                                           AND ----Pramod Mathew : New team OET is added :10th April 2007---
                                               dddca_order_details.ordertype =
                                                  vproductname
                                           AND (   dddca_customer.customertype =
                                                      'I'
                                                OR dddca_customer.customertype
                                                      IS NULL)
                                           AND dddca_order_details.teamid <>
                                                  nteamid
                                           AND dddca_order_details.sdteam =
                                                  nteamid
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         END IF;
      /*************************************************/
      --Getting View Orders
      /*************************************************/
      ELSIF UPPER (vtype) = 'V'
      THEN
         IF vproductname = 'MPLS'
         THEN
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.creator_person_id <>
                                                   nuserid)
                                           AND (   dddca_order_details.delegatedto <>
                                                      nuserid
                                                OR dddca_order_details.delegatedto
                                                      IS NULL)
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND (   dddca_order_details.orderstatus IN ('SALES IN PROGRESS',
                                                                                       'REJECTED TO SALES')
                                                OR (    dddca_order_details.orderstatus =
                                                           'CANCELED BY SD'
                                                    AND (   dddca_order_details.delegatedbyteamid =
                                                               nteamid
                                                         OR dddca_order_details.delegatedbyteamid =
                                                               nteamid)))
                                           AND dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY dddca_order_details.order_id DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --Service delivery
            ELSIF (nteamcategoryid = 2)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.orderowner <>
                                                   nuserid)
                                           AND (   dddca_order_details.delegatedto <>
                                                      nuserid
                                                OR dddca_order_details.delegatedto
                                                      IS NULL)
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND (dddca_order_details.orderstatus IN ('SUBMIT TO SD',
                                                                                    'REJECTED TO SD'))
                                           AND dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --RCE budapest and pune.
            ELSIF    (nteamcategoryid = 5)
                  OR (nteamcategoryid = 4)
                  OR (nteamcategoryid = 6)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.rceowner <>
                                                   nuserid)
                                           AND (   dddca_order_details.delegatedto <>
                                                      nuserid
                                                OR dddca_order_details.delegatedto
                                                      IS NULL)
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND (    dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                        'SUBMIT TO OET')
                                                AND dddca_order_details.ordertype =
                                                       vproductname)
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         ELSIF vproductname = 'SPRING'
         THEN
            --spring
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              ORDER_ID,
                              ORDER_REFERENCE,
                              CLIENT_ACCOUNT_ID,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              MENU_FLAG,
                              menu_name,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'SPRING'
                                        AND (dddca_order_details.orderowner <>
                                                nuserid)
                                        AND (   dddca_order_details.delegatedto <>
                                                   nuserid
                                             OR dddca_order_details.delegatedto
                                                   IS NULL)
                                        AND dddca_order_details.orderowner
                                               IS NOT NULL
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) =
                                                    'ORIGINATED BY SPRING'
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF vproductname = 'FCE'
         THEN                      ---gousiya- fce view orders-26th april 2007
            --fce
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              CLIENT_ACCOUNT_ID,
                              client_name,
                              received_date,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'FCE'
                                        AND (dddca_order_details.orderowner <>
                                                nuserid)
                                        AND (   dddca_order_details.delegatedto <>
                                                   nuserid
                                             OR dddca_order_details.delegatedto
                                                   IS NULL)
                                        AND dddca_order_details.orderowner
                                               IS NOT NULL
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) =
                                                    'ORIGINATED BY FCE'
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'GSEARCH'
      THEN
         IF vproductname = 'MPLS'
         THEN
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND --(DDDCA_ORDER_DETAILS.CREATOR_PERSON_ID <> nUserid) AND
                                               --(DDDCA_ORDER_DETAILS.DELEGATEDTO <> nUserId OR  DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) AND
                                               --DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
                                               --(DDDCA_ORDER_DETAILS.ORDERSTATUS IN ('SALES IN PROGRESS','REJECTED TO SALES')
                                               --OR
                                               --(DDDCA_ORDER_DETAILS.ORDERSTATUS = 'CANCELED BY SD'
                                               -- AND
                                               -- (DDDCA_ORDER_DETAILS.DELEGATEDBYTEAMID = nTeamId OR DDDCA_ORDER_DETAILS.delegatedbyteamid = nTeamId)
                                               --)
                                               --) AND
                                               dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (vort_flag)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --Service delivery
            ELSIF (nteamcategoryid = 2)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND --(DDDCA_ORDER_DETAILS.ORDEROWNER <> nUserid ) AND
                                               --(DDDCA_ORDER_DETAILS.DELEGATEDTO <> nUserId OR  DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) AND
                                               --DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
                                               --(DDDCA_ORDER_DETAILS.ORDERSTATUS IN ('SUBMIT TO SD','REJECTED TO SD')) AND
                                               dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (vort_flag)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --RCE budapest and pune.
            ELSIF    (nteamcategoryid = 5)
                  OR (nteamcategoryid = 4)
                  OR (nteamcategoryid = 6)
            THEN   --Pramod Mathew : New team OET is added :10th April 2007---
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 orderstatus,
                                 customer_id,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC)
                                    RN,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND --(DDDCA_ORDER_DETAILS.RCEOWNER <> nUserid ) AND
                                               --(DDDCA_ORDER_DETAILS.DELEGATEDTO <> nUserId OR  DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) AND
                                               --DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
                                               --(DDDCA_ORDER_DETAILS.ORDERSTATUS = 'SUBMIT TO RCE' AND
                                               dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (vort_flag)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         ELSIF vproductname = 'SPRING'
         THEN
            --spring
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              ORDER_ID,
                              ORDER_REFERENCE,
                              CLIENT_ACCOUNT_ID,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              menu_flag,
                              menu_name,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        AND UPPER (ordertype) = 'SPRING'
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        --(DDDCA_ORDER_DETAILS.ORDEROWNER <> nUserid) AND
                                        --(DDDCA_ORDER_DETAILS.DELEGATEDTO <> nUserid OR  DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) AND
                                        --DDDCA_ORDER_DETAILS.ORDEROWNER IS NOT NULL AND
                                        --DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
                                        -- DDDCA_ORDER_DETAILS.CREATOR_PERSON_ID IS NULL AND
                                        --(UPPER(DDDCA_ORDER_DETAILS.ORDERSTATUS) = 'ORIGINATED BY SPRING' AND
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF vproductname = 'FCE'
         THEN       ---gousiya--- fec vies orders for GSEARCH -26th april 2007
            --FCE
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        AND UPPER (ordertype) = 'FCE'
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        --(DDDCA_ORDER_DETAILS.ORDEROWNER <> nUserid) AND
                                        --(DDDCA_ORDER_DETAILS.DELEGATEDTO <> nUserid OR  DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) AND
                                        --DDDCA_ORDER_DETAILS.ORDEROWNER IS NOT NULL AND
                                        --DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
                                        -- DDDCA_ORDER_DETAILS.CREATOR_PERSON_ID IS NULL AND
                                        --(UPPER(DDDCA_ORDER_DETAILS.ORDERSTATUS) = 'ORIGINATED BY SPRING' AND
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      /*************************************************/
      --Getting Unassigned orders for SPRING
      /*************************************************/
      ELSIF UPPER (vtype) = 'UA'
      THEN
         IF vproductname = 'SPRING'
         THEN
            --spring
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              order_id,
                              order_reference,
                              client_account_id,
                              Client_Name,
                              received_date,
                              menu_flag,
                              menu_name,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           DDDCA_ORDER_DETAILS.ORDER_ID)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc,
                                        dddca_customer
                                  WHERE     dddca_customer.order_id =
                                               dddca_order_details.order_id
                                        AND dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND dddca_order_details.ordertype =
                                               'SPRING'
                                        AND (dddca_order_details.orderowner
                                                IS NULL)
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    dddca_order_details.orderstatus =
                                                    'Originated by Spring'
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                                        AND (   dddca_customer.mastercustomerid IN (SELECT customer_id
                                                                                      FROM dddca_cust_usergroup_mapping
                                                                                     WHERE    user_group_id IN (SELECT user_group_id
                                                                                                                  FROM dddca_user_usergroup_mapping
                                                                                                                 WHERE sysuserid =
                                                                                                                          nuserid)
                                                                                           OR user_group_id NOT IN (SELECT user_group_id
                                                                                                                      FROM dddca_user_usergroup_mapping))
                                             OR dddca_customer.mastercustomerid NOT IN (SELECT customer_id
                                                                                          FROM dddca_cust_usergroup_mapping))
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF vproductname = 'FCE'
         THEN           ---gousiya--unassigned orders for fce--26th april 2007
            --fce
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              received_date,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT dddca_order_details.order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc,
                                        dddca_customer
                                  WHERE     dddca_customer.order_id =
                                               dddca_order_details.order_id
                                        AND dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND dddca_order_details.ordertype =
                                               'FCE'
                                        AND (dddca_order_details.orderowner
                                                IS NULL)
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    dddca_order_details.orderstatus =
                                                    'Originated by FCE'
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                                        AND dddca_customer.mastercustomerid NOT IN (SELECT dcm.customer_id
                                                                                      FROM dddca_cust_usergroup_mapping dcm,
                                                                                           dddca_user_usergroup_mapping dum
                                                                                     WHERE     dcm.user_group_id =
                                                                                                  dum.user_group_id
                                                                                           AND dum.sysuserid <>
                                                                                                  nuserid)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      /*************************************************/
      --Getting Delegated Orders
      /*************************************************/
      ELSIF UPPER (vtype) = 'D'
      THEN
         --Sales
         IF (nteamcategoryid = 1)
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              orderstatus,
                              customer_id,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (dddca_order_details.creator_person_id =
                                                nuserid)
                                        AND dddca_order_details.delegatedto <>
                                               nuserid
                                        AND (   dddca_order_details.delegatedby
                                                   IS NOT NULL
                                             OR dddca_order_details.delegatedby =
                                                   nuserid)
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND dddca_order_details.delegatedbyteamid =
                                               nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --Service delivery
         ELSIF (nteamcategoryid = 2)
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              orderstatus,
                              customer_id,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND dddca_order_details.delegatedto <>
                                               nuserid
                                        AND (   dddca_order_details.delegatedby
                                                   IS NOT NULL
                                             OR dddca_order_details.delegatedby =
                                                   nuserid)
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND dddca_order_details.delegatedbyteamid =
                                               nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF nteamcategoryid = 3
         THEN                                                         --SPRING
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              ORDER_ID,
                              ORDER_REFERENCE,
                              CLIENT_ACCOUNT_ID,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              menu_flag,
                              menu_name,
                              SORT_ID,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'SPRING'
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND dddca_order_details.delegatedto <>
                                               nuserid
                                        AND (   dddca_order_details.delegatedby
                                                   IS NOT NULL
                                             OR dddca_order_details.delegatedby =
                                                   nuserid)
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND dddca_order_details.delegatedbyteamid =
                                               nteamid
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                                        AND DDDCA_ORDER_DETAILS.DELEGATEREASON <>
                                               'AAF Approval Required'
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF nteamcategoryid = 7
         THEN               --gousiya--delegated order for FCE-26th april 2007
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              CLIENT_ACCOUNT_ID,
                              client_name,
                              received_date,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'FCE'
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND dddca_order_details.delegatedto <>
                                               nuserid
                                        AND (   dddca_order_details.delegatedby
                                                   IS NOT NULL
                                             OR dddca_order_details.delegatedby =
                                                   nuserid)
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND dddca_order_details.delegatedbyteamid =
                                               nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --RCE
         ELSIF    (nteamcategoryid = 5)
               OR (nteamcategoryid = 4)
               OR (nteamcategoryid = 6)
         THEN      --Pramod Mathew : New team OET is added :10th April 2007---
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              created_date,
                              orderstatus,
                              CUSTOMER_ID,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND dddca_order_details.rceowner =
                                               nuserid
                                        AND dddca_order_details.delegatedto <>
                                               nuserid
                                        AND (   dddca_order_details.delegatedby
                                                   IS NOT NULL
                                             OR dddca_order_details.delegatedby =
                                                   nuserid)
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND dddca_order_details.delegatedbyteamid =
                                               nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      /*************************************************/
      --Getting Canceled Orders for MPLS
      /*************************************************/
      ELSIF UPPER (vtype) = 'C'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Canceled Orders for MPLS
            /*************************************************/
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 ORDERSTATUS,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.creator_person_id =
                                                   nuserid)
                                           AND dddca_order_details.canceledby
                                                  IS NOT NULL
                                           AND dddca_order_details.orderstatus =
                                                  'CANCELED ORDER'
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --Service delivery
            ELSIF (nteamcategoryid = 2)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 created_date,
                                 ORDERSTATUS,
                                 CUSTOMER_ID,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC)
                                    RN,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.orderowner =
                                                   nuserid)
                                           AND dddca_order_details.canceledby
                                                  IS NOT NULL
                                           AND dddca_order_details.orderstatus =
                                                  'CANCELED BY SD'
                                           AND dddca_order_details.canceledbyteam =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         ELSIF vproductname = 'SPRING'
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              ORDER_ID,
                              ORDER_REFERENCE,
                              CLIENT_ACCOUNT_ID,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              MENU_FLAG,
                              menu_name,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'SPRING'
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) IN ('CANCELED BY SD',
                                                                                         'CANCELED ORDER')
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            /*OPEN v_cursor FOR
            SELECT DDDCA_ORDER_DETAILS.ORDER_ID,
            DDDCA_CUSTOMER.CUSTOMERNAME,
            DDDCA_ORDER_DETAILS.CREATED_DATE,
            DDDCA_ORDER_DETAILS.ORDERSTATUS
            FROM DDDCA_ORDER_DETAILS, DDDCA_CUSTOMER
            WHERE
            DDDCA_ORDER_DETAILS.ORDER_ID =   DDDCA_CUSTOMER.ORDER_ID (+) AND
            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserId)  AND
            DDDCA_ORDER_DETAILS.CANCELEDBY IS NOT NULL AND
            DDDCA_ORDER_DETAILS.ORDERSTATUS IN ('CANCELED BY SD','CANCELED ORDER') AND
            DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
            DDDCA_ORDER_DETAILS.ORDERTYPE = 'SPRING'
            ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC;
            */
            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF vproductname = 'FCE'
         THEN             ---gousiya--canceled orders for fce--26th april 2007
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              received_date,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'FCE'
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) IN ('CANCELED BY SD',
                                                                                         'CANCELED ORDER')
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            /*OPEN v_cursor FOR
            SELECT DDDCA_ORDER_DETAILS.ORDER_ID,
            DDDCA_CUSTOMER.CUSTOMERNAME,
            DDDCA_ORDER_DETAILS.CREATED_DATE,
            DDDCA_ORDER_DETAILS.ORDERSTATUS
            FROM DDDCA_ORDER_DETAILS, DDDCA_CUSTOMER
            WHERE
            DDDCA_ORDER_DETAILS.ORDER_ID =   DDDCA_CUSTOMER.ORDER_ID (+) AND
            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserId)  AND
            DDDCA_ORDER_DETAILS.CANCELEDBY IS NOT NULL AND
            DDDCA_ORDER_DETAILS.ORDERSTATUS IN ('CANCELED BY SD','CANCELED ORDER') AND
            DDDCA_ORDER_DETAILS.TEAMID = nTeamId AND
            DDDCA_ORDER_DETAILS.ORDERTYPE = 'SPRING'
            ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC;
            */
            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      /*************************************************/
      --Getting Canceled Orders BY SD for MPLS
      /*************************************************/
      ELSIF UPPER (vtype) = 'CBSD'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Canceled Orders for MPLS
            /*************************************************/
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND dddca_order_details.creator_person_id =
                                                  nuserid
                                           AND dddca_order_details.delegatedby
                                                  IS NULL
                                           AND dddca_order_details.canceledby <>
                                                  nuserid
                                           AND dddca_order_details.orderstatus =
                                                  'CANCELED BY SD'
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         END IF;
      ELSIF UPPER (vtype) = 'R'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Rejected Orders for SD.
            /*************************************************/
            --Sales
            IF (nteamcategoryid = 2)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.orderowner =
                                                   nuserid)
                                           AND (dddca_order_details.rejectedby
                                                   IS NOT NULL)
                                           AND dddca_order_details.orderstatus =
                                                  'REJECTED TO SALES'
                                           AND dddca_order_details.rejectedbyteam =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            ELSIF    (nteamcategoryid = 5)
                  OR (nteamcategoryid = 4)
                  OR (nteamcategoryid = 6)
            THEN   --Pramod Mathew : New team OET is added :10th April 2007---
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.rceowner =
                                                   nuserid)
                                           AND (dddca_order_details.rejectedby
                                                   IS NOT NULL)
                                           AND dddca_order_details.orderstatus IN ('REJECTED TO SD',
                                                                                   'REJECTED TO SALES')
                                           AND --dddca_order_details.REJECTEDBYTEAM = nTeamId
                                               dddca_order_details.rceteam =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         END IF;
      ELSIF UPPER (vtype) = 'RBSD'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Rejected BY SD Orders TO DISPLAY FOR SALES
            /*************************************************/
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND dddca_order_details.creator_person_id =
                                                  nuserid
                                           AND dddca_order_details.delegatedby
                                                  IS NULL
                                           AND dddca_order_details.rejectedby <>
                                                  nuserid
                                           AND dddca_order_details.orderstatus =
                                                  'REJECTED TO SALES'
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         END IF;
      ELSIF UPPER (vtype) = 'RBRCE'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting Rejected BY RCE Orders TO DISPLAY FOR SALES
            /*************************************************/
            --RCE
            IF nteamcategoryid = 2
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (    dddca_order_details.orderowner =
                                                       nuserid
                                                AND dddca_order_details.rejectedby <>
                                                       nuserid)
                                           AND dddca_order_details.delegatedby
                                                  IS NULL
                                           AND dddca_order_details.orderstatus =
                                                  'REJECTED TO SD'
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            ELSIF (   nteamcategoryid = 5
                   OR nteamcategoryid = 4
                   OR nteamcategoryid = 6)
            THEN   --Pramod Mathew : New team OET is added :10th April 2007---
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 ORDERSTATUS,
                                 CUSTOMER_ID,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC)
                                    RN,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (   dddca_order_details.orderowner =
                                                      nuserid
                                                OR dddca_order_details.rejectedby <>
                                                      nuserid)
                                           AND dddca_order_details.delegatedby
                                                  IS NULL
                                           AND dddca_order_details.orderstatus =
                                                  'REJECTED TO SD'
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY dddca_order_details.order_id DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         END IF;
      ELSIF UPPER (vtype) = 'VBEND'
      THEN
         IF vproductname = 'MPLS'
         THEN
            /*************************************************/
            --Getting BEND Orders TO DISPLAY FOR SD
            /*************************************************/
            --SERVICE DELIVERY
            IF (nteamcategoryid = 2)
            THEN
               SELECT countryname
                 INTO vcountry
                 FROM dddca_teamcountries, dddca_team
                WHERE     dddca_teamcountries.teamid = dddca_team.teamid
                      AND dddca_teamcountries.teamid = nteamid;

               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 orderstatus,
                                 customer_id,
                                 menu_flag,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY order_id DESC)
                                    rn,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT DISTINCT
                                           dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details,
                                           dddca_customer,
                                           dddca_site,
                                           dddca_site_location
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND dddca_order_details.order_id =
                                                  dddca_site.order_id
                                           AND dddca_site.site_id =
                                                  dddca_site_location.site_id
                                           AND dddca_site_location.country =
                                                  vcountry
                                           AND UPPER (
                                                  dddca_order_details.ort_flag) =
                                                  UPPER (vort_flag)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         END IF;
      ELSIF UPPER (vtype) = 'UTC'
      THEN
         -- End-Added by Sanveer for Service Activation Staff - CR 20385
         /*************************************************/
         --Getting Orders Uploaded to classic TO DISPLAY FOR sales
         /*************************************************/
         --Sales
         IF (nteamcategoryid = 1)
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              ORDERSTATUS,
                              CUSTOMER_ID,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC',
                                                                                         'UPLOADING IN PROGRESS...'))
                                             OR (dddca_order_details.orderstatus IN ('REQUEST CREATED')))
                                        AND dddca_order_details.creator_person_id =
                                               nuserid
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND teamid = nteamid
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --SD
         ELSIF (nteamcategoryid = 2)
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              ORDERSTATUS,
                              CUSTOMER_ID,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC',
                                                                                         'UPLOADING IN PROGRESS...'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF UPPER (vproductname) = 'SPRING'
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              order_id,
                              order_reference,
                              client_account_id,
                              Client_Name,
                              received_date,
                              MENU_FLAG,
                              MENU_NAME,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND UPPER (
                                               dddca_order_details.ordertype) =
                                               UPPER (vproductname)
                                        AND teamid = nteamid
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF UPPER (vproductname) = 'FCE'
         THEN
            --gousiya--Orders Uploaded to classic TO DISPLAY FOR fce--26th april 2007
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND dddca_order_details.orderowner =
                                               nuserid
                                        AND UPPER (
                                               dddca_order_details.ordertype) =
                                               UPPER (vproductname)
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --RCE
         ELSIF (   nteamcategoryid = 5
                OR nteamcategoryid = 4
                OR nteamcategoryid = 6)
         THEN      --Pramod Mathew : New team OET is added :10th April 2007---
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              ORDERSTATUS,
                              CUSTOMER_ID,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC',
                                                                                         'UPLOADING IN PROGRESS...'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND dddca_order_details.rceowner =
                                               nuserid
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND teamid = nteamid
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTCT'
      THEN
         -- End-Added by Sanveer for Service Activation Staff - CR 20385
         /*************************************************/
         --Getting Orders Uploaded to classic TO DISPLAY FOR sales
         /*************************************************/
         --Sales
         IF (nteamcategoryid = 1)
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              ORDERSTATUS,
                              CUSTOMER_ID,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC',
                                                                                         'UPLOADING IN PROGRESS...'))
                                             OR (dddca_order_details.orderstatus IN ('REQUEST CREATED')))
                                        AND --DDDCA_ORDER_DETAILS.CREATOR_PERSON_ID = nUserId AND
                                            dddca_order_details.ordertype =
                                               vproductname
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --SD
         ELSIF (nteamcategoryid = 2)
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              ORDERSTATUS,
                              CUSTOMER_ID,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC',
                                                                                         'UPLOADING IN PROGRESS...'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND --DDDCA_ORDER_DETAILS.ORDEROWNER = nUserId AND
                                            dddca_order_details.ordertype =
                                               vproductname
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY dddca_order_details.order_id DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF UPPER (vproductname) = 'SPRING'
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              order_id,
                              order_reference,
                              client_account_id,
                              Client_Name,
                              received_date,
                              MENU_FLAG,
                              MENU_NAME,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND --DDDCA_ORDER_DETAILS.ORDEROWNER = nUserId AND
                                            UPPER (
                                               dddca_order_details.ordertype) =
                                               UPPER (vproductname)
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY dddca_order_details.order_id DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF UPPER (vproductname) = 'FCE'
         THEN
            --gousiya--Orders Uploaded to classic TO DISPLAY FOR fce--26th april 2007
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              received_date,
                              menu_flag,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND --DDDCA_ORDER_DETAILS.ORDEROWNER = nUserId AND
                                            UPPER (
                                               dddca_order_details.ordertype) =
                                               UPPER (vproductname)
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY dddca_order_details.order_id DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --RCE
         ELSIF (   nteamcategoryid = 5
                OR nteamcategoryid = 4
                OR nteamcategoryid = 6)
         THEN      --Pramod Mathew : New team OET is added :10th April 2007---
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              customername,
                              CREATED_DATE,
                              ORDERSTATUS,
                              CUSTOMER_ID,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        dddca_order_details.order_id,
                                        dddca_customer.customername,
                                        dddca_order_details.created_date,
                                        dddca_order_details.orderstatus,
                                        dddca_customer.mastercustomerid
                                           AS customer_id,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details, dddca_customer
                                  WHERE     dddca_order_details.order_id =
                                               dddca_customer.order_id(+)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC',
                                                                                         'UPLOADING IN PROGRESS...'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND --DDDCA_ORDER_DETAILS.RCEOWNER = nUserId AND
                                            dddca_order_details.ordertype =
                                               vproductname
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY dddca_order_details.order_id DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      -- Start-Added by Sanveer for Service Activation Staff - CR 20385
      ELSIF UPPER (vtype) = 'SAS'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              order_id,
                              order_reference,
                              client_account_id,
                              Client_Name,
                              received_date,
                              MENU_FLAG,
                              MENU_NAME,
                              SORT_ID,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND UPPER (
                                               dddca_order_details.ordertype) =
                                               UPPER (vproductname)
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY dddca_order_details.order_id DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;

         IF UPPER (vproductname) = 'FCE'
         THEN                                       --gousiya--26th april 2007
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (    (   dddca_order_details.pass1status =
                                                            'T'
                                                      OR dddca_order_details.pass2status =
                                                            'T')
                                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                                         'UPLOAD PASS2 TO CLASSIC'))
                                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                                        AND UPPER (
                                               dddca_order_details.ordertype) =
                                               UPPER (vproductname)
                                        AND teamid = nteamid
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      -- END-Added by Sanveer for Service Activation Staff - CR 20385
      /*ELSIF UPPER(vType) = 'SAST' THEN
      IF UPPER(vProductName) = 'SPRING' THEN
      OPEN v_cursor FOR
      SELECT
      Order_Id,
      dd_dca_pk.CONCAT_ORDERREFERENCE(DDDCA_ORDER_DETAILS.Order_Id) as ORDER_REFERENCE,
      --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
      CLIENT_ACCOUNT_ID,
      CLIENT_NAME AS "Client Name",
      TO_CHAR(ORDER_RECEIVED_DATE,'DD-MON-YYYY') RECEIVED_DATE,
      vType AS MENU_FLAG,
      (SELECT MENUNAME FROM DDDCA_MENU WHERE MENUURL = 'OrderList.aspx?Flag=' || vType) AS MENU_NAME
      FROM
      DDDCA_ORDER_DETAILS,
      DDDCA_REUTERS_CLIENT drc
      WHERE
      DDDCA_ORDER_DETAILS.order_id = drc.ORDERID
      --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
      --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
      --Show "Provide" order as before (MOD_STATUS IS NULL).
      AND (DDDCA_ORDER_DETAILS.MOD_STATUS = 'Y' OR DDDCA_ORDER_DETAILS.MOD_STATUS IS NULL)
      --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
      AND (DDDCA_ORDER_DETAILS.INFLIGHT_SCENARIO_ID NOT IN('SCENARIO1','SCENARIO2','SCENARIO4','SCENARIO5') OR DDDCA_ORDER_DETAILS.INFLIGHT_SCENARIO_ID IS NULL)
      AND
      (
      ((DDDCA_ORDER_DETAILS.PASS1STATUS = 'T' OR DDDCA_ORDER_DETAILS.PASS2STATUS = 'T') AND
      DDDCA_ORDER_DETAILS.ORDERSTATUS  IN ('UPLOAD PASS1 TO CLASSIC','UPLOAD PASS2 TO CLASSIC'))
      OR
      DDDCA_ORDER_DETAILS.ORDERSTATUS  IN ('REQUEST CREATED')
      )
      AND
      UPPER(DDDCA_ORDER_DETAILS.ORDERTYPE) = UPPER(vProductName)
      AND upper(DDDCA_ORDER_DETAILS.ORT_FLAG) = upper(vORT_FLAG)
      AND TEAMID = nTeamId
      ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC;
      io_cursor := v_cursor;
      vReturnValue:='OK';
      END IF;
      */
      ELSIF UPPER (vtype) = 'VP'
      THEN
         IF vproductname = 'MPLS'
         THEN
            --Sales
            IF (nteamcategoryid = 1)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 ORDERSTATUS,
                                 CUSTOMER_ID,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC)
                                    RN,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.creator_person_id <>
                                                   nuserid)
                                           AND (   dddca_order_details.delegatedto <>
                                                      nuserid
                                                OR dddca_order_details.delegatedto
                                                      IS NULL)
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND dddca_order_details.profile_id =
                                                  nprofileid
                                           AND (   dddca_order_details.orderstatus IN ('SALES IN PROGRESS',
                                                                                       'REJECTED TO SALES')
                                                OR (    dddca_order_details.orderstatus =
                                                           'CANCELED BY SD'
                                                    AND dddca_order_details.delegatedbyteamid =
                                                           nteamid))
                                           AND dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY dddca_order_details.order_id DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --Service delivery
            ELSIF (nteamcategoryid = 2)
            THEN
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 ORDERSTATUS,
                                 CUSTOMER_ID,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC)
                                    RN,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.orderowner <>
                                                   nuserid)
                                           AND (   dddca_order_details.delegatedto <>
                                                      nuserid
                                                OR dddca_order_details.delegatedto
                                                      IS NULL)
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND dddca_order_details.profile_id =
                                                  nprofileid
                                           AND (dddca_order_details.orderstatus IN ('SUBMIT TO SD',
                                                                                    'REJECTED TO SD'))
                                           AND dddca_order_details.ordertype =
                                                  vproductname
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY dddca_order_details.order_id DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            --RCE budapest and pune.
            ELSIF    (nteamcategoryid = 5)
                  OR (nteamcategoryid = 4)
                  OR (nteamcategoryid = 6)
            THEN   --Pramod Mathew : New team OET is added :10th April 2007---
               OPEN V_CURSOR FOR
                  SELECT *
                    FROM (SELECT order_id,
                                 customername,
                                 CREATED_DATE,
                                 ORDERSTATUS,
                                 CUSTOMER_ID,
                                 MENU_FLAG,
                                 MENU_NAME,
                                 ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC)
                                    RN,
                                 COUNT (*) OVER () cnt
                            FROM (  SELECT dddca_order_details.order_id,
                                           dddca_customer.customername,
                                           dddca_order_details.created_date,
                                           dddca_order_details.orderstatus,
                                           dddca_customer.mastercustomerid
                                              AS customer_id,
                                           vtype AS menu_flag,
                                           (SELECT menuname
                                              FROM dddca_menu
                                             WHERE menuurl =
                                                         'OrderList.aspx?Flag='
                                                      || vtype)
                                              AS menu_name
                                      FROM dddca_order_details, dddca_customer
                                     WHERE     dddca_order_details.order_id =
                                                  dddca_customer.order_id(+)
                                           AND (dddca_order_details.rceowner <>
                                                   nuserid)
                                           AND (   dddca_order_details.delegatedto <>
                                                      nuserid
                                                OR dddca_order_details.delegatedto
                                                      IS NULL)
                                           AND dddca_order_details.teamid =
                                                  nteamid
                                           AND dddca_order_details.profile_id =
                                                  nprofileid
                                           AND (    dddca_order_details.orderstatus IN ('SUBMIT TO RCE',
                                                                                        'SUBMIT TO OET')
                                                AND dddca_order_details.ordertype =
                                                       vproductname)
                                           AND UPPER (
                                                  DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                                  UPPER (VORT_FLAG)
                                  ORDER BY DDDCA_ORDER_DETAILS.ORDER_ID DESC))
                   WHERE rn BETWEEN v_pg_start AND v_pg_end;

               io_cursor := v_cursor;
               vreturnvalue := 'OK';
            END IF;
         ELSIF vproductname = 'SPRING'
         THEN
            --spring
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              order_id,
                              order_reference,
                              client_account_id,
                              Client_Name,
                              received_date,
                              MENU_FLAG,
                              MENU_NAME,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        --ORDER_REFERENCE,  Sukanta Sengupta,24-Dec-2007 for Order baseline Design
                                        --CLIENT_ACCOUNT_ID,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        --CLIENT_NAME AS "Client Name",
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'SPRING'
                                        AND (dddca_order_details.orderowner <>
                                                nuserid)
                                        AND (   dddca_order_details.delegatedto <>
                                                   nuserid
                                             OR dddca_order_details.delegatedto
                                                   IS NULL)
                                        AND dddca_order_details.orderowner
                                               IS NOT NULL
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.profile_id =
                                               nprofileid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) =
                                                    'ORIGINATED BY SPRING'
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               DDDCA_ORDER_DETAILS.ORT_FLAG) =
                                               UPPER (VORT_FLAG)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         ELSIF vproductname = 'FCE'
         THEN                                       --gousiya--26th april 2007
            --FCE
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              CLIENT_NAME,
                              RECEIVED_DATE,
                              MENU_FLAG,
                              MENU_NAME,
                              ROW_NUMBER () OVER (ORDER BY ORDER_ID DESC) RN,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT order_id,
                                        order_reference,
                                        client_account_id,
                                        client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid
                                        --Yogesh J. Shah, 22 Sep 2006 - Show only "Decomp" order (whose MOD_STATUS = 'Y')
                                        --and not "Inventory" order in the front-end in case of "Mods and Cease" order.
                                        --Show "Provide" order as before (MOD_STATUS IS NULL).
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        --Do not show Inflight orders of scenario 1, 2, 4, 5 in the order list.
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'FCE'
                                        AND (dddca_order_details.orderowner <>
                                                nuserid)
                                        AND (   dddca_order_details.delegatedto <>
                                                   nuserid
                                             OR dddca_order_details.delegatedto
                                                   IS NULL)
                                        AND dddca_order_details.orderowner
                                               IS NOT NULL
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.profile_id =
                                               nprofileid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) =
                                                    'ORIGINATED BY FCE'
                                             AND dddca_order_details.ordertype =
                                                    vproductname)
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                               ORDER BY ORDER_ID DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'AAF'
      THEN
         --Added by Kanakshree For R18_GSCE_58240
         IF vproductname = 'SPRING'
         THEN
            --spring
            OPEN V_CURSOR FOR
               SELECT *
                 FROM (SELECT DISTINCT
                              order_id,
                              order_reference,
                              client_account_id,
                              Client_Name,
                              received_date,
                              MENU_FLAG,
                              MENU_NAME,
                              sort_id,
                              BACK_COLOR,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        DD_DCA_PK.CONCAT_ACCOUNTID (
                                           DDDCA_ORDER_DETAILS.ORDER_ID,
                                           '/')
                                           AS CLIENT_ACCOUNT_ID,
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS Client_Name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND (   (dddca_order_details.delegatedto =
                                                    nuserid)
                                             OR (    dddca_order_details.orderowner =
                                                        nuserid
                                                 AND dddca_order_details.delegatedto
                                                        IS NULL)
                                             OR (    dddca_order_details.orderowner =
                                                        nuserid
                                                 AND dddca_order_details.delegatedto =
                                                        nuserid))
                                        AND (   (    dddca_order_details.teamid =
                                                        nteamid
                                                 AND dddca_order_details.delegatedtoteamid
                                                        IS NULL)
                                             OR dddca_order_details.delegatedtoteamid =
                                                   nteamid)
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                         'ORDER SUBMISSION ERROR')
                                             AND UPPER (
                                                    dddca_order_details.ordertype) =
                                                    UPPER (vproductname)
                                             AND (   pass1status <> 'T'
                                                  OR pass1status IS NULL))
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                                        AND dddca_order_details.delegatereason =
                                               'AAF Approval Required'
                               ORDER BY SORT_ID, ORDER_ID ASC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         --Added by Kanakshree For R18_GSCE_58240
         END IF;
      END IF;

      io_cursor := v_cursor;
      VRETURNVALUE := 'OK';
   END getorders;

   -------------------------------
   --Author: Amol Kashikar
   --Date:11/04/2006

   -- Purpose:
   --This procedure is used to Get USER'S profile on online DCA.

   --Output Parameter:  Cursor:
   PROCEDURE getuserprofile (nuserid IN NUMBER, io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT DISTINCT
                  dddca_profile.profileid,
                     dddca_profile.profilename
                  || ' - '
                  || dddca_pk_user_pro.teamname (dddca_user_profile.teamid)
                     AS profilename,
                  dddca_profile.profileid || ',' || dddca_user_profile.teamid
                     AS profileteamid,
                  dddca_user_profile.defaultflag
             FROM dddca_profile,
                  dddca_user_profile,
                  dddca_team,
                  dddca_sysuser
            WHERE     dddca_profile.profileid = dddca_user_profile.profileid
                  AND dddca_team.teamid = dddca_user_profile.teamid
                  AND dddca_sysuser.sysuserid = dddca_user_profile.sysuserid
                  AND dddca_sysuser.sysuserid = nuserid
         ORDER BY dddca_user_profile.defaultflag;

      io_cursor := v_cursor;
   END getuserprofile;

   -------------------------------
   --Author: Amol Kashikar
   --Date:11/04/2006

   -- Purpose:
   --This procedure is used to Get USER'S profile on online DCA.

   --Output Parameter:  Cursor:
   PROCEDURE saveuserdefaultprofile (nuserid        IN     NUMBER,
                                     nprofileid     IN     NUMBER,
                                     nteamid        IN     NUMBER,
                                     vreturnvalue      OUT VARCHAR2)
   IS
      nuserprofileid      NUMBER;
      nnewuserprofileid   NUMBER;
      ndefaultcount       NUMBER;
      icount              NUMBER;
   BEGIN
      --Getting earlier default profile for user.
      SELECT COUNT (userprofileid)
        INTO ndefaultcount
        FROM dddca_user_profile
       WHERE sysuserid = nuserid AND defaultflag = 'T';

      UPDATE dddca_order_details
         SET delegatedtoteamid = nteamid
       WHERE delegatedto = nuserid AND delegatedtoteamid IS NULL;

      IF (ndefaultcount) > 0
      THEN
         --Getting earlier default userprofileId for that user.
         SELECT userprofileid
           INTO nuserprofileid
           FROM dddca_user_profile
          WHERE sysuserid = nuserid AND defaultflag = 'T';

         UPDATE dddca_user_profile
            SET defaultflag = NULL
          WHERE userprofileid = nuserprofileid;

         --Getting new userprofileid to update default profile.
         SELECT userprofileid
           INTO nnewuserprofileid
           FROM dddca_user_profile
          WHERE     sysuserid = nuserid
                AND profileid = nprofileid
                AND teamid = nteamid;

         --Updating new profile id as default.
         UPDATE dddca_user_profile
            SET defaultflag = 'T'
          WHERE userprofileid = nnewuserprofileid;

         vreturnvalue := 'OK';
      ELSE
         SELECT userprofileid
           INTO nnewuserprofileid
           FROM dddca_user_profile
          WHERE     sysuserid = nuserid
                AND profileid = nprofileid
                AND teamid = nteamid;

         UPDATE dddca_user_profile
            SET defaultflag = 'T'
          WHERE userprofileid = nnewuserprofileid;

         vreturnvalue := 'OK';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         vreturnvalue := 'ERROR';
   END saveuserdefaultprofile;

   -------------------------------
   --Author: Sanveer
   --Date:02/08/2006

   -- Purpose:
   --This procedure is used to Get Submission Queue.

   --Output Parameter:  Cursor: Team Category, Errro Description
   PROCEDURE getsubmission_queue (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
         SELECT submission_id,
                btsubsidary,
                dddca_pk_user_pro.teamname (sdteam_id) AS team,
                sd_group_email AS groupemail,
                sd_ort_group_email AS ortgroupemail,
                'N/A' AS product,
                'SD' AS teamcategory
           FROM dddca_submissionqueue
          WHERE rceteam_id IS NULL
         UNION
         SELECT submission_id,
                btsubsidary,
                dddca_pk_user_pro.teamname (rceteam_id) AS team,
                rce_group_email AS groupemail,
                rce_ort_group_email AS ortgroupemail,
                dddca_product.productname AS product,
                'RCE' AS teamcategory
           FROM dddca_submissionqueue, dddca_product
          WHERE     sdteam_id IS NULL
                AND dddca_submissionqueue.product_id =
                       dddca_product.productid
         ORDER BY btsubsidary;

      io_cursor := v_cursor;
   END getsubmission_queue;

   PROCEDURE getcountryaccessmodel (io_cursor OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      OPEN v_cursor FOR
           SELECT dddca_prod_cont_model.prodcontmodelid,
                  dddca_prod_cont_model.country,
                  dddca_product.productname,
                  dddca_model.modelname
             FROM dddca_prod_cont_model, dddca_model, dddca_product
            WHERE     dddca_prod_cont_model.modelid = dddca_model.modelid
                  AND dddca_product.productid = dddca_prod_cont_model.productid
         ORDER BY country;

      io_cursor := v_cursor;
   END getcountryaccessmodel;

   PROCEDURE advancesearch (vproduct       IN     VARCHAR2,
                            nfrom          IN     VARCHAR2,
                            nto            IN     NUMBER,
                            voriginator    IN     VARCHAR2,
                            nteamid        IN     NUMBER,
                            vorderstatus   IN     VARCHAR2,
                            io_cursor         OUT t_cursor)
   IS
      v_cursor   t_cursor;
   BEGIN
      IF INSTR (nfrom, '%') > 0
      THEN
         OPEN v_cursor FOR
              SELECT dddca_order_details.order_id,
                     (SELECT fulllegalcompanyname
                        FROM dddca_customer
                       WHERE     dddca_customer.order_id =
                                    dddca_order_details.order_id
                             AND dddca_customer.customertype = 'I')
                        AS customer,
                     orderstatus,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = creator_person_id)
                        AS creator,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = orderowner)
                        AS sdowner,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = rceowner)
                        AS rceowner,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = delegatedto)
                        AS delegated,
                     (SELECT MAX (c_order_id)
                        FROM dca_cla_batch
                       WHERE d_order_id = dddca_order_details.order_id)
                        AS classicorderid,
                     teamid
                FROM dddca_order_details
               WHERE     order_id LIKE nfrom
                     /*(
                       ( ORDER_ID >= nFrom AND ORDER_ID < nTo )  --TO SEARCH ONE ORDER SEND nTo as nFrom
                      OR
                      ( nFrom = -1 OR nTo = -1 ) --No Filter on Order Id
                     )   */
                     AND creator_person_id IN (SELECT sysuserid
                                                 FROM dddca_sysuser
                                                WHERE UPPER (username) LIKE
                                                         UPPER (
                                                               '%'
                                                            || voriginator
                                                            || '%'))
                     AND (teamid = nteamid                --FOR SPECIFIED TEAM
                                          OR nteamid = 0       -- FOR ALL TEAM
                                                        )
                     AND (orderstatus = vorderstatus OR vorderstatus = 'ALL')
                     AND ordertype = vproduct
            ORDER BY order_id DESC;
      ELSE
         OPEN v_cursor FOR
              SELECT dddca_order_details.order_id,
                     (SELECT fulllegalcompanyname
                        FROM dddca_customer
                       WHERE     dddca_customer.order_id =
                                    dddca_order_details.order_id
                             AND dddca_customer.customertype = 'I')
                        AS customer,
                     orderstatus,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = creator_person_id)
                        AS creator,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = orderowner)
                        AS sdowner,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = rceowner)
                        AS rceowner,
                     (SELECT username
                        FROM dddca_sysuser
                       WHERE sysuserid = delegatedto)
                        AS delegated,
                     (SELECT MAX (c_order_id)
                        FROM dca_cla_batch
                       WHERE d_order_id = dddca_order_details.order_id)
                        AS classicorderid,
                     teamid
                FROM dddca_order_details
               WHERE     (   (order_id >= nfrom AND order_id < nto) --TO SEARCH ONE ORDER SEND nTo as nFrom
                          OR (nfrom = -1 OR nto = -1)  --No Filter on Order Id
                                                     )
                     AND creator_person_id IN (SELECT sysuserid
                                                 FROM dddca_sysuser
                                                WHERE UPPER (username) LIKE
                                                         UPPER (
                                                               '%'
                                                            || voriginator
                                                            || '%'))
                     AND (teamid = nteamid                --FOR SPECIFIED TEAM
                                          OR nteamid = 0       -- FOR ALL TEAM
                                                        )
                     AND (orderstatus = vorderstatus OR vorderstatus = 'ALL')
                     AND ordertype = vproduct
            ORDER BY order_id DESC;
      END IF;

      io_cursor := v_cursor;
   END advancesearch;

   PROCEDURE orderuploadstatus (norderid   IN     NUMBER,
                                smsgtype      OUT VARCHAR2,
                                smsgdesc      OUT VARCHAR2,
                                srefresh      OUT VARCHAR)
   IS
      sclsorderid       VARCHAR2 (200);
      sstatus           VARCHAR2 (50);
      smsgkey           VARCHAR2 (50);
      -----------modified by arindam for SIT defect 22286 -----------
      v_cnt_classicid   NUMBER;
      v_count           NUMBER;

      CURSOR c_classic_ids
      IS
         SELECT DISTINCT c_order_id
           FROM dca_cla_batch
          WHERE d_order_id = norderid;
   BEGIN
        SELECT status, msg_seq_out
          INTO sstatus, smsgkey
          FROM dca_cla_interaction
         WHERE     d_order_id = norderid
               AND status_date = (SELECT MAX (status_date)
                                    FROM dca_cla_interaction
                                   WHERE d_order_id = norderid)
      ORDER BY status_date DESC;

      IF UPPER (sstatus) = 'SUCCESS'
      THEN
         -----------modified by arindam for SIT defect 22286 -----------
         v_cnt_classicid := 0;

         SELECT COUNT (DISTINCT c_order_id)
           INTO v_cnt_classicid
           FROM dca_cla_batch
          WHERE d_order_id = norderid;

         -------------------------------------------------------------------
         IF v_cnt_classicid = 1
         THEN
            SELECT DISTINCT c_order_id
              INTO sclsorderid
              FROM dca_cla_batch
             WHERE d_order_id = norderid AND q_batch_id = 1;

            smsgtype := 'SUCCESS';
            smsgdesc :=
                  'Order '
               || norderid
               || ' is successfully created in Classic. Classic Order Id is '
               || sclsorderid;
            srefresh := 'N';
         END IF;                       --- END OF  IF v_Cnt_ClassicId = 1 THEN

         IF v_cnt_classicid > 1
         THEN
            v_count := 0;

            FOR v_classic_id IN c_classic_ids
            LOOP
               v_count := v_count + 1;

               IF v_count = 1
               THEN
                  sclsorderid := v_classic_id.c_order_id;
               ELSE
                  sclsorderid :=
                     TRIM (sclsorderid) || ',' || v_classic_id.c_order_id;
               END IF;
            END LOOP;

            smsgtype := 'SUCCESS';
            smsgdesc :=
                  'Order '
               || norderid
               || ' is successfully created in Classic. Classic Order Ids are '
               || sclsorderid;
            srefresh := 'N';
         END IF;                        --- end of IF v_Cnt_ClassicId > 1 THEN
      -----------------------------------------------------------------------
      ELSIF UPPER (sstatus) = 'FAIL'
      THEN
         smsgtype := 'FAIL';
         smsgdesc :=
               'Order '
            || norderid
            || ' is Failed. Please Click Show Error for error Description';
         srefresh := 'N';
      ELSE
         SELECT status
           INTO sstatus
           FROM dca_cla_message
          WHERE msg_key = smsgkey;

         IF UPPER (sstatus) = 'IDLE'
         THEN
            smsgtype := 'IDLE';
            smsgdesc :=
                  'Request is successfully Created and Pending to upload for Order '
               || norderid;
            srefresh := 'Y';
         ELSIF UPPER (sstatus) = 'SENT'
         THEN
            smsgtype := 'SENT';
            smsgdesc :=
                  'Request is successfully Created and waiting for Response from Classic for Order '
               || norderid;
            srefresh := 'Y';
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         smsgtype := 'ERROR';
         smsgdesc := 'Error in Retriving Order Uploading Status';
         srefresh := 'Y';
   END orderuploadstatus;

   --CR:210980: Single Site Rejection
   PROCEDURE dca_copy_rejected_sites (p_i_parent_order_id   IN     NUMBER,
                                      p_i_child_order_id    IN     NUMBER,
                                      p_o_status               OUT VARCHAR2)
   IS
      -----------------------------------------------------------------------------------------------------------------------
      --
      -- PROCEDURE DCA_COPY_REJECTED_SITES
      --
      -----------------------------------------------------------------------------------------------------------------------
      --
      -- Scope: Public
      -- Description: This Procedure copies the Rejected Site Details from one
      --     MPLS for a given order ID. i.e. it creates a new order ID
      --     whose details are same as that of the original order ID. The duplication is done at
      --    order level and site level. i.e. "Customer Details", "Customer Contact Details",
      --    "Distributor Contact Details", "Order Contact Details", "Special Bid", "Additional Information" and
      --    "Product Selection" details will be copied to other order
      --     plus the order id of rejected sites will be updated with new order id
      --
      -- Returns: "OK" if successful, or error message in case of an exception.
      --
      -- Modification History:
      -- Version  Date           Modifier          Description
      -- ------- ----------      ----------        -----------
      -- 1.0     28th May 2007   Sanjay Hotwani    Initial creation of function.
      -----------------------------------------------------------------------------------------------------------------------
      v_new_order_id            NUMBER;
      v_new_customer_id         NUMBER;
      v_order_details           dddca_order_details%ROWTYPE;
      v_customer                dddca_customer%ROWTYPE;
      v_order_contact           dddca_order_contact%ROWTYPE;
      v_specialbid              dddca_specialbid%ROWTYPE;
      v_additionalinformation   dddca_additionalinformation%ROWTYPE;
      v_order_product           dddca_order_product%ROWTYPE;
      v_cos                     dddca_order_product.cosmodel%TYPE;

      CURSOR c_order_details
      IS
         SELECT *
           FROM dddca_order_details
          WHERE order_id = p_i_parent_order_id;

      CURSOR c_customer
      IS
         SELECT *
           FROM dddca_customer
          WHERE order_id = p_i_parent_order_id;

      CURSOR c_order_contact
      IS
         SELECT *
           FROM dddca_order_contact
          WHERE orderid = p_i_parent_order_id;

      CURSOR c_specialbid
      IS
         SELECT *
           FROM dddca_specialbid
          WHERE order_id = p_i_parent_order_id;

      CURSOR c_additionalinformation
      IS
         SELECT *
           FROM dddca_additionalinformation
          WHERE order_id = p_i_parent_order_id;

      CURSOR c_order_product
      IS
         SELECT *
           FROM dddca_order_product
          WHERE order_id = p_i_parent_order_id;
   BEGIN
      ------------------------------------------------------------------------------------------------------
      --Assign the new order ID value to "P_O_DUPLICATE_ORDER_ID" parameter.
      v_new_order_id := p_i_child_order_id;

      OPEN c_order_details;

      --There is only 1 record to be duplicated.
      FETCH c_order_details INTO v_order_details;

      IF c_order_details%FOUND
      THEN
         --We have to insert the new Order ID for the order details to be duplicated.
         v_order_details.order_id := v_new_order_id;
         --Update some values of order details before duplication.
         v_order_details.created_date := SYSDATE;
         v_order_details.orderstatus := 'Rejected By SD';

         --Insert the duplicate order details.
         INSERT INTO dddca_order_details
              VALUES v_order_details;
      END IF;

      CLOSE c_order_details;

      ------------------------------------------------------------------------------------------------------

      --Get the new Customer ID for the order details to be duplicated.
      SELECT seq_customer.NEXTVAL INTO v_new_customer_id FROM DUAL;

      OPEN c_customer;

      --There is only 1 record to be duplicated.
      FETCH c_customer INTO v_customer;

      IF c_customer%FOUND
      THEN
         --We have to insert the new Customer ID and Order ID for the customer details to be duplicated.
         v_customer.customer_id := v_new_customer_id;
         v_customer.order_id := v_new_order_id;
         v_customer.orderformsigndate := NULL;

         -- Beeresh, 28-Jun-2006 , Defect 7383

         --Insert the duplicate customer details.
         INSERT INTO dddca_customer
              VALUES v_customer;
      END IF;

      CLOSE c_customer;

      ------------------------------------------------------------------------------------------------------
      OPEN c_order_contact;

      --There are multiple records to be duplicated.
      LOOP
         FETCH c_order_contact INTO v_order_contact;

         EXIT WHEN c_order_contact%NOTFOUND;
         --We have to insert the new Order ID for the order contact details to be duplicated.
         v_order_contact.orderid := v_new_order_id;

         --Insert the duplicate order contact details.
         INSERT INTO dddca_order_contact
              VALUES v_order_contact;
      END LOOP;

      CLOSE c_order_contact;

      ------------------------------------------------------------------------------------------------------
      OPEN c_specialbid;

      --There is only 1 record to be duplicated.
      FETCH c_specialbid INTO v_specialbid;

      IF c_specialbid%FOUND
      THEN
         --We have to insert the new Order ID for the special bid details to be duplicated.
         v_specialbid.order_id := v_new_order_id;

         --Insert the duplicate special bid details.
         INSERT INTO dddca_specialbid
              VALUES v_specialbid;
      END IF;

      CLOSE c_specialbid;

      ------------------------------------------------------------------------------------------------------
      OPEN c_additionalinformation;

      --There is only 1 record to be duplicated.
      FETCH c_additionalinformation INTO v_additionalinformation;

      IF c_additionalinformation%FOUND
      THEN
         --We have to insert the new Order ID for the additional information details to be duplicated.
         v_additionalinformation.order_id := v_new_order_id;

         --Insert the duplicate additional information details.
         INSERT INTO dddca_additionalinformation
              VALUES v_additionalinformation;
      END IF;

      CLOSE c_additionalinformation;

      ------------------------------------------------------------------------------------------------------
      OPEN c_order_product;

      --There is only 1 record to be duplicated.
      FETCH c_order_product INTO v_order_product;

      IF c_order_product%FOUND
      THEN
         --To Find Cos Model Value
         v_cos := v_order_product.cosmodel;
         --We have to insert the new Order ID for the order product details to be duplicated.
         v_order_product.order_id := v_new_order_id;
         --Added By Beeresh for Defect7226
         v_order_product.lockflag := '';

         --Insert the duplicate order product details.
         INSERT INTO dddca_order_product
              VALUES v_order_product;
      END IF;

      CLOSE c_order_product;

      ------------------------------------------------------------------------------------------------------
      --Replace Parent Order Id with Child Order Id for Rejected Sites
      FOR vrejectedsites
         IN (SELECT *
               FROM dddca_partialrejected_order
              WHERE     parent_order_id = p_i_parent_order_id
                    AND child_order_id = p_i_child_order_id)
      LOOP
         UPDATE dddca_site
            SET order_id = p_i_child_order_id
          WHERE     order_id = p_i_parent_order_id
                AND site_id = vrejectedsites.site_id;

         --Update VPN Order Id
         UPDATE dddca_vpn
            SET orderid = p_i_child_order_id
          WHERE     orderid = p_i_parent_order_id
                AND siteid = vrejectedsites.site_id;

         --Update COS Details
         IF UPPER (v_cos) = 'DSCP COS'
         THEN
            UPDATE dddca_dscpcos_parameters
               SET order_id = p_i_child_order_id
             WHERE     order_id = p_i_parent_order_id
                   AND siteid = vrejectedsites.site_id;
         ELSE
            UPDATE dddca_3cos_parameters
               SET order_id = p_i_child_order_id
             WHERE     order_id = p_i_parent_order_id
                   AND virtual_id = vrejectedsites.site_id
                   AND access_type = 'Standard DSL';
         END IF;

         --Update Pricing Details
         --Dipankar Changes Table Name
         UPDATE dca_commele_price
            SET order_id = p_i_child_order_id
          WHERE     order_id = p_i_parent_order_id
                AND site_id = vrejectedsites.site_id;
      END LOOP;

      UPDATE dddca_partialrejected_order
         SET completed_flag = 'Y'
       WHERE     parent_order_id = p_i_parent_order_id
             AND child_order_id = p_i_child_order_id;

      COMMIT;
      p_o_status := 'OK';
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         ROLLBACK;
         p_o_status := 'No data found.';
      WHEN DUP_VAL_ON_INDEX
      THEN
         ROLLBACK;
         p_o_status :=
            'The same primary key value (ID) already exists in the database.';
      WHEN VALUE_ERROR
      THEN
         ROLLBACK;
         p_o_status := 'Invalid constraining of numeric or character data.';
      WHEN OTHERS
      THEN
         ROLLBACK;
         p_o_status := 'Error in copy of Rejected Sites';
   END dca_copy_rejected_sites;

   --CR:226471 : Order Save Functionality
   PROCEDURE get_orderstatus (p_order_id       IN     NUMBER,
                              p_order_status      OUT VARCHAR2)
   AS
      order_status   VARCHAR2 (100);
   BEGIN
      BEGIN
         SELECT orderstatus
           INTO order_status
           FROM dddca_order_details
          WHERE ordertype = 'MPLS' AND order_id = p_order_id;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            order_status := NULL;
         WHEN OTHERS
         THEN
            order_status := NULL;
      END;

      IF order_status IS NOT NULL
      THEN
         p_order_status := order_status;
      END IF;
   END get_orderstatus;

   --Comment BEGIN by Sujit Kumar Sahu on 24-Dec-2008 for enhance performance enhancement of query
   --query that are used in Front End is to be called from back end once for comparision of value
   PROCEDURE get_internalordertype (vorderid           IN     NUMBER,
                                    viordertypeunion      OUT VARCHAR2,
                                    viordertype           OUT VARCHAR2,
                                    verrordesc            OUT VARCHAR2)
   IS
      v_iordertypeunion   dddca_customer.ordertype%TYPE := NULL;
      v_iordertype        dddca_customer.ordertype%TYPE := NULL;
   BEGIN
      BEGIN
         SELECT ordertype
           INTO v_iordertypeunion
           FROM dddca_customer
          WHERE order_id = vorderid AND UPPER (customertype) = 'S';
      EXCEPTION
         WHEN OTHERS
         THEN
            viordertypeunion := NULL;
      END;

      BEGIN
         SELECT is_internal_order
           INTO v_iordertype
           FROM dddca_order_details
          WHERE order_id = vorderid;
      EXCEPTION
         WHEN OTHERS
         THEN
            viordertype := NULL;
      END;

      IF v_iordertypeunion IS NOT NULL
      THEN
         viordertypeunion := v_iordertypeunion;
      ELSE
         viordertypeunion := NULL;
      END IF;

      IF v_iordertype IS NOT NULL
      THEN
         viordertype := v_iordertype;
      ELSE
         viordertype := NULL;
      END IF;

      verrordesc := 'OK';
      RETURN;
   EXCEPTION
      WHEN OTHERS
      THEN
         verrordesc := 'NOT OK';
   END get_internalordertype;

   --Comment END by Sujit Kumar Sahu on 24-Dec-2008 for enhance performance enhancement of query

   --Comment BEGIN by Sujit Kumar Sahu on 08-Jan-2009 for enhance performance of Search In All Order in Orderlist Page
   PROCEDURE getsearchinallqueueorderid (
      nuserid            IN     NUMBER,
      nteamid            IN     NUMBER,
      vproductname       IN     VARCHAR2,
      vtype              IN     VARCHAR,
      nteamcategoryid    IN OUT NUMBER,
      vort_flag          IN     VARCHAR,
      nprofileid         IN     NUMBER,
      io_cursor             OUT t_cursor,
      vreturnvalue          OUT VARCHAR2,
      vsearchallorders   IN     NUMBER)
   IS
      v_cursor          t_cursor;
      vcountry          VARCHAR2 (100);
      icount            NUMBER;
      newteamcategory   NUMBER;
   BEGIN
      newteamcategory := nteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      SELECT COUNT (*)
        INTO icount                           -----gousiya -- 26th april 2007;
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.fce_flag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 7;
      END IF;

      nteamcategoryid := newteamcategory;

      --Getting Pending Orders
      IF UPPER (vtype) = 'P'
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN                  --Comment by Sujit Kumar Sahu on 08-Jan-2009
               --Getting Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              AND (   (dddca_order_details.delegatedto =
                                          nuserid)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto
                                              IS NULL)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto =
                                              nuserid))
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND dddca_order_details.order_id =
                                     vsearchallorders
                              AND (   dddca_order_details.delegatereason NOT IN ('More Info Required',
                                                                                 'Rejected',
                                                                                 'Approved')
                                   OR dddca_order_details.delegatereason
                                         IS NULL)
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      --END  OF Pending Orders

      --Getting View Orders
      ELSIF UPPER (vtype) = 'V'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (dddca_order_details.orderowner <> nuserid)
                        AND (   dddca_order_details.delegatedto <> nuserid
                             OR dddca_order_details.delegatedto IS NULL)
                        AND dddca_order_details.orderowner IS NOT NULL
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) =
                                    'ORIGINATED BY SPRING'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
                        AND (   dddca_order_details.delegatereason NOT IN ('More Info Required',
                                                                           'Rejected',
                                                                           'Approved')
                             OR dddca_order_details.delegatereason IS NULL)
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'GSEARCH'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND dddca_order_details.ordertype = vproductname
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Unassigned orders for SPRING
      ELSIF UPPER (vtype) = 'UA'
      THEN                        --Comment by Sujit Kumar Sahu on 08-Jan-2009
         IF vproductname = 'SPRING'
         THEN
            --spring
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        dddca_order_details.order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details,
                        dddca_reuters_client drc,
                        dddca_customer
                  WHERE     dddca_customer.order_id =
                               dddca_order_details.order_id
                        AND dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND dddca_order_details.ordertype = 'SPRING'
                        AND (dddca_order_details.orderowner IS NULL)
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    dddca_order_details.orderstatus =
                                    'Originated by Spring'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
                        AND (   dddca_customer.mastercustomerid IN (SELECT customer_id
                                                                      FROM dddca_cust_usergroup_mapping
                                                                     WHERE    user_group_id IN (SELECT user_group_id
                                                                                                  FROM dddca_user_usergroup_mapping
                                                                                                 WHERE sysuserid =
                                                                                                          nuserid)
                                                                           OR user_group_id NOT IN (SELECT user_group_id
                                                                                                      FROM dddca_user_usergroup_mapping))
                             OR dddca_customer.mastercustomerid NOT IN (SELECT customer_id
                                                                          FROM dddca_cust_usergroup_mapping))
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Delegated Orders
      ELSIF UPPER (vtype) = 'D'
      THEN
         IF nteamcategoryid = 3
         THEN  --SPRING           --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND dddca_order_details.orderowner = nuserid
                        AND dddca_order_details.delegatedto <> nuserid
                        AND (   dddca_order_details.delegatedby IS NOT NULL
                             OR dddca_order_details.delegatedby = nuserid)
                        AND dddca_order_details.ordertype = vproductname
                        AND dddca_order_details.delegatedbyteamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
                        --Added by Kanakshree FOr R22_GSCE_85005_eDCA_Delegation
                        AND dddca_order_details.delegatereason <>
                               'AAF Approval Required'
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Canceled Orders for MPLS
      ELSIF UPPER (vtype) = 'C'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND dddca_order_details.orderowner = nuserid
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) IN ('CANCELED BY SD',
                                                                             'CANCELED ORDER')
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTC'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND dddca_order_details.orderowner = nuserid
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTCT'
      THEN
         --Getting Orders Uploaded to classic TO DISPLAY FOR sales
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'SAS'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'VP'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            --spring
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (dddca_order_details.orderowner <> nuserid)
                        AND (   dddca_order_details.delegatedto <> nuserid
                             OR dddca_order_details.delegatedto IS NULL)
                        AND dddca_order_details.orderowner IS NOT NULL
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.profile_id = nprofileid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) =
                                    'ORIGINATED BY SPRING'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND dddca_order_details.order_id = vsearchallorders
                        AND dddca_order_details.delegatereason NOT IN ('More Info Required',
                                                                       'Rejected',
                                                                       'Approved')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Added by Kanakshree For R18_GSCE_58240
      ELSIF UPPER (vtype) = 'AAF'
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN
               --Getting AAF Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              AND (   (dddca_order_details.delegatedto =
                                          nuserid)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto
                                              IS NULL)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto =
                                              nuserid))
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND dddca_order_details.order_id =
                                     vsearchallorders
                              AND dddca_order_details.delegatereason =
                                     'AAF Approval Required'
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      ---Added by Kanakshree For R22_GSCE_85005_eDCA_Delegation on 10/07/2012
      ELSIF UPPER (vtype) = 'AAFP'       ---<<<AAF Authorised Pending orders>>
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN
               --Getting AAF Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              /* AND (   (dddca_order_details.delegatedto =
                                                                     nuserid
                                       )
                                    OR (    dddca_order_details.orderowner =
                                                                     nuserid
                                        AND dddca_order_details.delegatedto IS NULL
                                       )
                                    OR (    dddca_order_details.orderowner =
                                                                     nuserid
                                        AND dddca_order_details.delegatedto =
                                                                     nuserid
                                       )
                                   )*/
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND dddca_order_details.order_id =
                                     vsearchallorders
                              AND dddca_order_details.delegatereason IN ('More Info Required',
                                                                         'Rejected',
                                                                         'Approved')
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      ELSIF UPPER (vtype) = 'AAFD'                ---<<<AAF Delegated orders>>
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN
               --Getting AAF Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              /*AND (   (dddca_order_details.delegatedto =
                                                                    nuserid
                                      )
                                   OR (    dddca_order_details.orderowner =
                                                                    nuserid
                                       AND dddca_order_details.delegatedto IS NULL
                                      )
                                   OR (    dddca_order_details.orderowner =
                                                                    nuserid
                                       AND dddca_order_details.delegatedto =
                                                                    nuserid
                                      )
                                  )*/
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND dddca_order_details.order_id =
                                     vsearchallorders
                              AND dddca_order_details.delegatereason =
                                     'AAF Approval Required'
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      ---Ended by Kanakshree For R22_GSCE_85005_eDCA_Delegation on 10/07/2012
      END IF;

      io_cursor := v_cursor;
      vreturnvalue := 'OK';
   END getsearchinallqueueorderid;

   PROCEDURE getsearchallqorderref (nuserid            IN     NUMBER,
                                    nteamid            IN     NUMBER,
                                    vproductname       IN     VARCHAR2,
                                    vtype              IN     VARCHAR,
                                    nteamcategoryid    IN OUT NUMBER,
                                    vort_flag          IN     VARCHAR,
                                    nprofileid         IN     NUMBER,
                                    io_cursor             OUT t_cursor,
                                    vreturnvalue          OUT VARCHAR2,
                                    vsearchallorders   IN     VARCHAR2)
   IS
      v_cursor          t_cursor;
      vcountry          VARCHAR2 (100);
      icount            NUMBER;
      newteamcategory   NUMBER;
   BEGIN
      newteamcategory := nteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      SELECT COUNT (*)
        INTO icount                           -----gousiya -- 26th april 2007;
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.fce_flag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 7;
      END IF;

      nteamcategoryid := newteamcategory;

      --Getting Pending Orders
      IF UPPER (vtype) = 'P'
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN                  --Comment by Sujit Kumar Sahu on 08-Jan-2009
               --Getting Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              AND (   (dddca_order_details.delegatedto =
                                          nuserid)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto
                                              IS NULL)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto =
                                              nuserid))
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND UPPER (
                                     RTRIM (
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/'))) LIKE
                                     (   '%'
                                      || UPPER (RTRIM (vsearchallorders))
                                      || '%')
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      --END  OF Pending Orders

      --Getting View Orders
      ELSIF UPPER (vtype) = 'V'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (dddca_order_details.orderowner <> nuserid)
                        AND (   dddca_order_details.delegatedto <> nuserid
                             OR dddca_order_details.delegatedto IS NULL)
                        AND dddca_order_details.orderowner IS NOT NULL
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) =
                                    'ORIGINATED BY SPRING'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'GSEARCH'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND dddca_order_details.ordertype = vproductname
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Unassigned orders for SPRING
      ELSIF UPPER (vtype) = 'UA'
      THEN                        --Comment by Sujit Kumar Sahu on 08-Jan-2009
         IF vproductname = 'SPRING'
         THEN
            --spring
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND ordertype = 'SPRING'
                        AND (dddca_order_details.orderowner IS NULL)
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    dddca_order_details.orderstatus =
                                    'Originated by Spring'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Delegated Orders
      ELSIF UPPER (vtype) = 'D'
      THEN
         IF nteamcategoryid = 3
         THEN  --SPRING           --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND dddca_order_details.orderowner = nuserid
                        AND dddca_order_details.delegatedto <> nuserid
                        AND (   dddca_order_details.delegatedby IS NOT NULL
                             OR dddca_order_details.delegatedby = nuserid)
                        AND dddca_order_details.ordertype = vproductname
                        AND dddca_order_details.delegatedbyteamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Canceled Orders for MPLS
      ELSIF UPPER (vtype) = 'C'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND dddca_order_details.orderowner = nuserid
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) IN ('CANCELED BY SD',
                                                                             'CANCELED ORDER')
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTC'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND dddca_order_details.orderowner = nuserid
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTCT'
      THEN
         --Getting Orders Uploaded to classic TO DISPLAY FOR sales
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'SAS'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'VP'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            --spring
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (dddca_order_details.orderowner <> nuserid)
                        AND (   dddca_order_details.delegatedto <> nuserid
                             OR dddca_order_details.delegatedto IS NULL)
                        AND dddca_order_details.orderowner IS NOT NULL
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.profile_id = nprofileid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) =
                                    'ORIGINATED BY SPRING'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_orderreference (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Added by Kanakshree for R18_GSCE_58240
      ELSIF UPPER (vtype) = 'AAF'
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN
               --Getting AAF Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              AND (   (dddca_order_details.delegatedto =
                                          nuserid)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto
                                              IS NULL)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto =
                                              nuserid))
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND UPPER (
                                     RTRIM (
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/'))) LIKE
                                     (   '%'
                                      || UPPER (RTRIM (vsearchallorders))
                                      || '%')
                              AND dddca_order_details.delegatereason =
                                     'AAF Approval Required'
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      END IF;

      io_cursor := v_cursor;
      vreturnvalue := 'OK';
   END getsearchallqorderref;

   PROCEDURE getsearchallqclientaccid (
      nuserid            IN     NUMBER,
      nteamid            IN     NUMBER,
      vproductname       IN     VARCHAR2,
      vtype              IN     VARCHAR,
      nteamcategoryid    IN OUT NUMBER,
      vort_flag          IN     VARCHAR,
      nprofileid         IN     NUMBER,
      io_cursor             OUT t_cursor,
      vreturnvalue          OUT VARCHAR2,
      vsearchallorders   IN     VARCHAR2)
   IS
      v_cursor          t_cursor;
      vcountry          VARCHAR2 (100);
      icount            NUMBER;
      newteamcategory   NUMBER;
   BEGIN
      newteamcategory := nteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      SELECT COUNT (*)
        INTO icount                           -----gousiya -- 26th april 2007;
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.fce_flag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 7;
      END IF;

      nteamcategoryid := newteamcategory;

      --Getting Pending Orders
      IF UPPER (vtype) = 'P'
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN                  --Comment by Sujit Kumar Sahu on 08-Jan-2009
               --Getting Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              AND (   (dddca_order_details.delegatedto =
                                          nuserid)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto
                                              IS NULL)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto =
                                              nuserid))
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND UPPER (
                                     RTRIM (
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/'))) LIKE
                                     (   '%'
                                      || UPPER (RTRIM (vsearchallorders))
                                      || '%')
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      --END  OF Pending Orders

      --Getting View Orders
      ELSIF UPPER (vtype) = 'V'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (dddca_order_details.orderowner <> nuserid)
                        AND (   dddca_order_details.delegatedto <> nuserid
                             OR dddca_order_details.delegatedto IS NULL)
                        AND dddca_order_details.orderowner IS NOT NULL
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) =
                                    'ORIGINATED BY SPRING'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'GSEARCH'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND dddca_order_details.ordertype = vproductname
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Unassigned orders for SPRING
      ELSIF UPPER (vtype) = 'UA'
      THEN                        --Comment by Sujit Kumar Sahu on 08-Jan-2009
         IF vproductname = 'SPRING'
         THEN
            --spring
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND ordertype = 'SPRING'
                        AND (dddca_order_details.orderowner IS NULL)
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    dddca_order_details.orderstatus =
                                    'Originated by Spring'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Delegated Orders
      ELSIF UPPER (vtype) = 'D'
      THEN
         IF nteamcategoryid = 3
         THEN  --SPRING           --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND dddca_order_details.orderowner = nuserid
                        AND dddca_order_details.delegatedto <> nuserid
                        AND (   dddca_order_details.delegatedby IS NOT NULL
                             OR dddca_order_details.delegatedby = nuserid)
                        AND dddca_order_details.ordertype = vproductname
                        AND dddca_order_details.delegatedbyteamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      --Getting Canceled Orders for MPLS
      ELSIF UPPER (vtype) = 'C'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND dddca_order_details.orderowner = nuserid
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) IN ('CANCELED BY SD',
                                                                             'CANCELED ORDER')
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTC'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND dddca_order_details.orderowner = nuserid
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'UTCT'
      THEN
         --Getting Orders Uploaded to classic TO DISPLAY FOR sales
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'SAS'
      THEN
         IF UPPER (vproductname) = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND (   (    (   dddca_order_details.pass1status = 'T'
                                      OR dddca_order_details.pass2status = 'T')
                                 AND dddca_order_details.orderstatus IN ('UPLOAD PASS1 TO CLASSIC',
                                                                         'UPLOAD PASS2 TO CLASSIC'))
                             OR dddca_order_details.orderstatus IN ('REQUEST CREATED'))
                        AND UPPER (dddca_order_details.ordertype) =
                               UPPER (vproductname)
                        AND teamid = nteamid
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY dddca_order_details.order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'VP'
      THEN
         IF vproductname = 'SPRING'
         THEN                     --Comment by Sujit Kumar Sahu on 08-Jan-2009
            --spring
            OPEN v_cursor FOR
                 SELECT DISTINCT
                        order_id,
                        dd_dca_pk.concat_orderreference (
                           dddca_order_details.order_id,
                           '/')
                           AS order_reference,
                        --CLIENT_ACCOUNT_ID,
                        dd_dca_pk.concat_accountid (
                           dddca_order_details.order_id,
                           '/')
                           AS client_account_id,
                        --CLIENT_NAME AS "Client Name",
                        dddca_pk_user_profile.concat_clientname (
                           dddca_order_details.order_id,
                           '/')
                           AS "Client Name",
                        TO_CHAR (order_received_date, 'DD-MON-YYYY')
                           received_date,
                        vtype AS menu_flag,
                        (SELECT menuname
                           FROM dddca_menu
                          WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                           AS menu_name,
                        sort_id,
                        dddca_pk_user_profile.get_backcolor_ordertype (
                           dddca_order_details.order_id)
                           AS back_color
                   FROM dddca_order_details, dddca_reuters_client drc
                  WHERE     dddca_order_details.order_id = drc.orderid(+)
                        AND (   dddca_order_details.mod_status = 'Y'
                             OR dddca_order_details.mod_status IS NULL)
                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                 'SCENARIO2',
                                                                                 'SCENARIO4',
                                                                                 'SCENARIO5')
                             OR dddca_order_details.inflight_scenario_id
                                   IS NULL)
                        AND UPPER (ordertype) = 'SPRING'
                        AND (dddca_order_details.orderowner <> nuserid)
                        AND (   dddca_order_details.delegatedto <> nuserid
                             OR dddca_order_details.delegatedto IS NULL)
                        AND dddca_order_details.orderowner IS NOT NULL
                        AND dddca_order_details.teamid = nteamid
                        AND dddca_order_details.profile_id = nprofileid
                        AND dddca_order_details.creator_person_id IS NULL
                        AND (    UPPER (dddca_order_details.orderstatus) =
                                    'ORIGINATED BY SPRING'
                             AND dddca_order_details.ordertype = vproductname)
                        AND UPPER (dddca_order_details.ort_flag) =
                               UPPER (vort_flag)
                        AND UPPER (
                               RTRIM (
                                  dd_dca_pk.concat_accountid (
                                     dddca_order_details.order_id,
                                     '/'))) LIKE
                               ('%' || UPPER (RTRIM (vsearchallorders)) || '%')
               ORDER BY order_id DESC;

            io_cursor := v_cursor;
            vreturnvalue := 'OK';
         END IF;
      ELSIF UPPER (vtype) = 'AAF'
      THEN
         BEGIN
            IF vproductname = 'SPRING'
            THEN                  --Comment by Sujit Kumar Sahu on 08-Jan-2009
               --Getting Pending Orders for Spring
               BEGIN
                  OPEN v_cursor FOR
                       SELECT DISTINCT
                              order_id,
                              dd_dca_pk.concat_orderreference (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS order_reference,
                              --CLIENT_ACCOUNT_ID,
                              dd_dca_pk.concat_accountid (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS client_account_id,
                              --CLIENT_NAME AS "Client Name",
                              dddca_pk_user_profile.concat_clientname (
                                 dddca_order_details.order_id,
                                 '/')
                                 AS "Client Name",
                              TO_CHAR (order_received_date, 'DD-MON-YYYY')
                                 received_date,
                              vtype AS menu_flag,
                              (SELECT menuname
                                 FROM dddca_menu
                                WHERE menuurl = 'OrderList.aspx?Flag=' || vtype)
                                 AS menu_name,
                              sort_id,
                              dddca_pk_user_profile.get_backcolor_ordertype (
                                 dddca_order_details.order_id)
                                 AS back_color
                         FROM dddca_order_details, dddca_reuters_client drc
                        WHERE     dddca_order_details.order_id = drc.orderid(+)
                              AND (   dddca_order_details.mod_status = 'Y'
                                   OR dddca_order_details.mod_status IS NULL)
                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                       'SCENARIO2',
                                                                                       'SCENARIO4',
                                                                                       'SCENARIO5')
                                   OR dddca_order_details.inflight_scenario_id
                                         IS NULL)
                              AND (   (dddca_order_details.delegatedto =
                                          nuserid)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto
                                              IS NULL)
                                   OR (    dddca_order_details.orderowner =
                                              nuserid
                                       AND dddca_order_details.delegatedto =
                                              nuserid))
                              AND (   (    dddca_order_details.teamid = nteamid
                                       AND dddca_order_details.delegatedtoteamid
                                              IS NULL)
                                   OR dddca_order_details.delegatedtoteamid =
                                         nteamid)
                              AND dddca_order_details.teamid = nteamid
                              AND dddca_order_details.creator_person_id IS NULL
                              AND (    UPPER (dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                   'ORDER SUBMISSION ERROR')
                                   AND UPPER (dddca_order_details.ordertype) =
                                          UPPER (vproductname)
                                   AND (   pass1status <> 'T'
                                        OR pass1status IS NULL))
                              AND UPPER (dddca_order_details.ort_flag) =
                                     UPPER (vort_flag)
                              AND UPPER (
                                     RTRIM (
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/'))) LIKE
                                     (   '%'
                                      || UPPER (RTRIM (vsearchallorders))
                                      || '%')
                              AND dddca_order_details.delegatereason =
                                     'AAF Approval Required'
                     ORDER BY sort_id, order_id ASC;

                  io_cursor := v_cursor;
                  vreturnvalue := 'OK';
               END;
            END IF;
         END;
      END IF;

      io_cursor := v_cursor;
      vreturnvalue := 'OK';
   END getsearchallqclientaccid;

   --Comment END by Sujit Kumar Sahu on 08-Jan-2009 for enhance performance of Search In All Order in Orderlist Page

   --Comment BEGIN by Sujit Kumar Sahu on 20-Apr-2009 for enhance performance of Search Page
   FUNCTION get_backcolor_ordertype (vorderid IN NUMBER)
      RETURN VARCHAR2
   IS
      v_ordertype    dddca_customer.ordertype%TYPE := NULL;
      v_isinternal   dddca_order_details.is_internal_order%TYPE := NULL;
      v_backcolor    VARCHAR2 (100) := NULL;
   BEGIN
      BEGIN
         SELECT UPPER (ordertype)
           INTO v_ordertype
           FROM dddca_customer
          WHERE order_id = vorderid AND UPPER (customertype) = 'S';
      EXCEPTION
         WHEN OTHERS
         THEN
            v_ordertype := NULL;
      END;

      BEGIN
         SELECT UPPER (is_internal_order)
           INTO v_isinternal
           FROM dddca_order_details
          WHERE order_id = vorderid;
      EXCEPTION
         WHEN OTHERS
         THEN
            v_isinternal := NULL;
      END;

      v_backcolor := NULL;

      IF v_ordertype IS NOT NULL
      THEN
         IF v_ordertype = 'ADD' AND v_isinternal = 'YES'
         THEN
            v_ordertype := 'INTERNAL ADD';
         ELSIF v_ordertype = 'MODIFY' AND v_isinternal = 'YES'
         THEN
            v_ordertype := 'INTERNAL MODIFY';
         END IF;

         IF v_ordertype = 'ADD'
         THEN
            v_backcolor := 'AliceBlue';
         ELSIF v_ordertype = 'MODIFY'
         THEN
            v_backcolor := '#ffc3ff';
         ELSIF v_ordertype = 'CEASE'
         THEN
            v_backcolor := 'LightGray';
         ELSIF v_ordertype = 'INFLIGHT'
         THEN
            v_backcolor := 'Ivory';
         ELSIF v_ordertype = 'MODIFY RCD ONLY'
         THEN
            v_backcolor := 'PowderBlue';
         ELSIF v_ordertype = 'INTERNAL ADD'
         THEN
            v_backcolor := 'LightSteelBlue';
         ELSIF v_ordertype = 'INTERNAL MODIFY'
         THEN
            v_backcolor := 'LightPink';
         END IF;
      ELSE
         v_backcolor := 'White';
      END IF;

      RETURN v_backcolor;
   EXCEPTION
      WHEN OTHERS
      THEN
         v_backcolor := 'White';
   END get_backcolor_ordertype;

   FUNCTION concat_clientname (p_order_id NUMBER, delimition VARCHAR2)
      RETURN VARCHAR2
   IS
      concatenated_clientname   VARCHAR2 (2000) := NULL;
   BEGIN
      FOR cur_rec IN (SELECT DISTINCT client_name
                        FROM dddca_reuters_client
                       WHERE orderid = p_order_id)
      LOOP
         IF delimition = '/'
         THEN
            concatenated_clientname :=
                  concatenated_clientname
               || cur_rec.client_name
               || ' '
               || '/'
               || ' ';
         ELSIF delimition = ','
         THEN
            concatenated_clientname :=
               concatenated_clientname || cur_rec.client_name || ',';
         END IF;
      END LOOP;

      RETURN SUBSTR (TRIM (concatenated_clientname),
                     1,
                     LENGTH (TRIM (concatenated_clientname)) - 1);
   END concat_clientname;

   --Comment END by Sujit Kumar Sahu on 20-Apr-2009 for enhance performance of Search Page
   ---Added by Kanakshree For R20_GSCE_71333_Delegation to bind the Order for AAF & AAFReports
   PROCEDURE getaaforders (nuserid           IN     NUMBER,
                           nteamid           IN     NUMBER,
                           vproductname      IN     VARCHAR2,
                           vtype             IN     VARCHAR,
                           nteamcategoryid   IN OUT NUMBER,
                           vort_flag         IN     VARCHAR,
                           nprofileid        IN     NUMBER,
                           V_PG_START        IN     NUMBER,-- added the attribute for pagination by shivani
                           v_pg_end          IN     NUMBER,-- added the attribute for pagination by shivani
                           io_cursor            OUT t_cursor,
                           vreturnvalue         OUT VARCHAR2)
   IS
      v_cursor               t_cursor;
      c_cursor               t_cursor;
      p_o_region_level_so    t_cursor;
      p_o_authorise_region   t_cursor;
      p_o_error_flag         VARCHAR2 (10);
      p_o_error_message      VARCHAR2 (400);
      p_o_error              VARCHAR (400);
      newteamcategory        NUMBER;
      icount                 NUMBER;
      vuserein               NUMBER;
      order_id               NUMBER;
      order_reference        VARCHAR2 (50);
      client_account_id      VARCHAR2 (120);
      client_name            VARCHAR2 (120);
      received_date          DATE;
      menu_name              VARCHAR2 (50);
      menu_flag              VARCHAR2 (50);
      sort_id                NUMBER;
      back_color             VARCHAR2 (50);
      authorisation_level    NUMBER;
      service_ownership      VARCHAR2 (50);
      regions                VARCHAR2 (50);
      p_o_authorise_level    NUMBER;
      authorise_region       VARCHAR2 (50);
   BEGIN
      newteamcategory := nteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      SELECT dddca_sysuser.ein
        INTO vuserein
        FROM dddca_sysuser
       WHERE dddca_sysuser.sysuserid = nuserid;

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      SELECT COUNT (*)
        INTO icount                           -----gousiya -- 26th april 2007;
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = nteamcategoryid
             AND dddca_teamcategory.fce_flag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 7;
      END IF;

      nteamcategoryid := newteamcategory;

      --Added by Kanakshree For R18_GSCE_58240
      IF UPPER (vtype) = 'AAF'
      THEN
         IF vproductname = 'SPRING'
         THEN
            --spring
            DELETE FROM dddca_aaf_record;

            --To get the SO , Auth Level,region
            pkg_spring_delegation.pr_get_region_level_so (
               vuserein,
               p_o_region_level_so,
               p_o_error_flag,
               p_o_error_message);

            LOOP
               FETCH p_o_region_level_so
                  INTO authorisation_level, service_ownership, regions;

               EXIT WHEN p_o_region_level_so%NOTFOUND;

               IF service_ownership = 'TR'
               THEN
                  OPEN v_cursor FOR
                     SELECT *                                       --added by shivani for pagination
                       FROM (SELECT order_id,
                                    order_reference,
                                    client_account_id,
                                    client_name,
                                    received_date,
                                    menu_flag,
                                    menu_name,
                                    ROW_NUMBER ()
                                       OVER (ORDER BY order_id DESC)
                                       rn,
                                    COUNT (*) OVER () cnt
                               FROM (  SELECT DISTINCT
                                              dddca_order_details.order_id,
                                              dd_dca_pk.concat_orderreference (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS order_reference,
                                              dd_dca_pk.concat_accountid (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_account_id,
                                              dddca_pk_user_profile.concat_clientname (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_name,
                                              TO_CHAR (order_received_date,
                                                       'DD-MON-YYYY')
                                                 received_date,
                                              vtype AS menu_flag,
                                              (SELECT menuname
                                                 FROM dddca_menu
                                                WHERE menuurl =
                                                            'OrderList.aspx?Flag='
                                                         || vtype)
                                                 AS menu_name,
                                              sort_id,
                                              dddca_pk_user_profile.get_backcolor_ordertype (
                                                 dddca_order_details.order_id)
                                                 AS back_color
                                         FROM dddca_order_details,
                                              dddca_reuters_client drc,
                                              --Changes Made for R20_GSCE_71333 on 04/01/2012 bu Kanakshree
                                              dddca_aaf_delegation dad,
                                              dddca_site ds
                                        WHERE     dddca_order_details.order_id =
                                                     drc.orderid(+)
                                              AND dddca_order_details.order_id =
                                                     ds.order_id
                                              AND ds.order_id =
                                                     dad.edca_order_id
                                              AND (   dddca_order_details.mod_status =
                                                         'Y'
                                                   OR dddca_order_details.mod_status
                                                         IS NULL)
                                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                       'SCENARIO2',
                                                                                                       'SCENARIO4',
                                                                                                       'SCENARIO5')
                                                   OR dddca_order_details.inflight_scenario_id
                                                         IS NULL)
                                              /*AND
                                             (
                                            (DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid) OR
                                            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) OR
                                            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid)
                                             )--Changes Made for R20_GSCE_71333 on 04/01/2012*/
                                              AND (   (    dddca_order_details.teamid =
                                                              nteamid
                                                       AND dddca_order_details.delegatedtoteamid
                                                              IS NULL)
                                                   OR dddca_order_details.delegatedtoteamid =
                                                         nteamid)
                                              AND dddca_order_details.teamid =
                                                     nteamid
                                              AND dddca_order_details.creator_person_id
                                                     IS NULL
                                              AND (    UPPER (
                                                          dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                               'ORDER SUBMISSION ERROR')
                                                   AND UPPER (
                                                          dddca_order_details.ordertype) =
                                                          UPPER (vproductname)
                                                   AND (   pass1status <> 'T'
                                                        OR pass1status IS NULL))
                                              AND UPPER (
                                                     dddca_order_details.ort_flag) =
                                                     UPPER (vort_flag)
                                              AND dddca_order_details.delegatereason =
                                                     'AAF Approval Required'
                                              ---Added by Kanakshree For R20_GSCE_71333 to getthe Pending orders only
                                              AND UPPER (ds.service_ownership) =
                                                     UPPER ('REUTERS')
                                              AND UPPER (
                                                     dad.finaldelegationtolevel) LIKE
                                                     CASE
                                                        WHEN UPPER (
                                                                authorisation_level) =
                                                                2
                                                        THEN
                                                           UPPER (
                                                              authorisation_level)
                                                        ELSE
                                                           '%' || NULL || '%'
                                                     END
                                              AND dad.isdelegationapproved = 0
                                              AND dad.region = regions
                                              AND dad.finaldelegationrequired =
                                                     1
                                     ORDER BY sort_id, order_id ASC))
                      WHERE rn BETWEEN v_pg_start AND v_pg_end;
               ELSIF service_ownership = 'FCE'
               THEN
                  OPEN v_cursor FOR
                     SELECT *                                --added by Shivani for pagination
                       FROM (SELECT order_id,
                                    order_reference,
                                    client_account_id,
                                    client_name,
                                    received_date,
                                    menu_flag,
                                    menu_name,
                                    ROW_NUMBER ()
                                       OVER (ORDER BY order_id DESC)
                                       rn,
                                    COUNT (*) OVER () cnt
                               FROM (  SELECT DISTINCT
                                              dddca_order_details.order_id,
                                              dd_dca_pk.concat_orderreference (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS order_reference,
                                              dd_dca_pk.concat_accountid (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_account_id,
                                              dddca_pk_user_profile.concat_clientname (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_name,
                                              TO_CHAR (order_received_date,
                                                       'DD-MON-YYYY')
                                                 received_date,
                                              vtype AS menu_flag,
                                              (SELECT menuname
                                                 FROM dddca_menu
                                                WHERE menuurl =
                                                            'OrderList.aspx?Flag='
                                                         || vtype)
                                                 AS menu_name,
                                              sort_id,
                                              dddca_pk_user_profile.get_backcolor_ordertype (
                                                 dddca_order_details.order_id)
                                                 AS back_color
                                         FROM dddca_order_details,
                                              dddca_reuters_client drc,
                                              --Changes Made for R20_GSCE_71333 on 04/01/2012 bu Kanakshree
                                              dddca_aaf_delegation dad,
                                              dddca_site ds
                                        WHERE     dddca_order_details.order_id =
                                                     drc.orderid(+)
                                              AND dddca_order_details.order_id =
                                                     ds.order_id
                                              AND ds.order_id =
                                                     dad.edca_order_id
                                              AND (   dddca_order_details.mod_status =
                                                         'Y'
                                                   OR dddca_order_details.mod_status
                                                         IS NULL)
                                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                       'SCENARIO2',
                                                                                                       'SCENARIO4',
                                                                                                       'SCENARIO5')
                                                   OR dddca_order_details.inflight_scenario_id
                                                         IS NULL)
                                              /*AND
                                             (
                                            (DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid) OR
                                            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) OR
                                            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid)
                                             )--Changes Made for R20_GSCE_71333 on 04/01/2012*/
                                              AND (   (    dddca_order_details.teamid =
                                                              nteamid
                                                       AND dddca_order_details.delegatedtoteamid
                                                              IS NULL)
                                                   OR dddca_order_details.delegatedtoteamid =
                                                         nteamid)
                                              AND dddca_order_details.teamid =
                                                     nteamid
                                              AND dddca_order_details.creator_person_id
                                                     IS NULL
                                              AND (    UPPER (
                                                          dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                               'ORDER SUBMISSION ERROR')
                                                   AND UPPER (
                                                          dddca_order_details.ordertype) =
                                                          UPPER (vproductname)
                                                   AND (   pass1status <> 'T'
                                                        OR pass1status IS NULL))
                                              AND UPPER (
                                                     dddca_order_details.ort_flag) =
                                                     UPPER (vort_flag)
                                              AND dddca_order_details.delegatereason =
                                                     'AAF Approval Required'
                                              ---Added by Kanakshree For R20_GSCE_71333 to getthe Pending orders only
                                              AND (   UPPER (
                                                         ds.service_ownership) =
                                                         UPPER ('FCE')
                                                   OR UPPER (
                                                         ds.service_ownership) =
                                                         UPPER ('ULTRA')
                                                   OR UPPER (
                                                         ds.service_ownership) =
                                                         UPPER (
                                                            'ULTRA AND FCE'))
                                              AND UPPER (
                                                     dad.finaldelegationtolevel) LIKE
                                                     CASE
                                                        WHEN UPPER (
                                                                authorisation_level) =
                                                                2
                                                        THEN
                                                           UPPER (
                                                              authorisation_level)
                                                        ELSE
                                                           '%' || NULL || '%'
                                                     END
                                              AND dad.isdelegationapproved = 0
                                              AND dad.region = regions
                                              AND dad.finaldelegationrequired =
                                                     1
                                     ORDER BY sort_id, order_id ASC))
                      WHERE rn BETWEEN v_pg_start AND v_pg_end;
               ELSIF UPPER (service_ownership) = 'MESHED'
               THEN
                  OPEN v_cursor FOR
                     SELECT *                                 --added by Shivani for pagination
                       FROM (SELECT order_id,
                                    order_reference,
                                    client_account_id,
                                    client_name,
                                    received_date,
                                    menu_flag,
                                    menu_name,
                                    ROW_NUMBER ()
                                       OVER (ORDER BY order_id DESC)
                                       rn,
                                    COUNT (*) OVER () cnt
                               FROM (  SELECT DISTINCT
                                              dddca_order_details.order_id,
                                              dd_dca_pk.concat_orderreference (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS order_reference,
                                              dd_dca_pk.concat_accountid (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_account_id,
                                              dddca_pk_user_profile.concat_clientname (
                                                 dddca_order_details.order_id,
                                                 '/')
                                                 AS client_name,
                                              TO_CHAR (order_received_date,
                                                       'DD-MON-YYYY')
                                                 received_date,
                                              vtype AS menu_flag,
                                              (SELECT menuname
                                                 FROM dddca_menu
                                                WHERE menuurl =
                                                            'OrderList.aspx?Flag='
                                                         || vtype)
                                                 AS menu_name,
                                              sort_id,
                                              dddca_pk_user_profile.get_backcolor_ordertype (
                                                 dddca_order_details.order_id)
                                                 AS back_color
                                         FROM dddca_order_details,
                                              dddca_reuters_client drc,
                                              --Changes Made for R20_GSCE_71333 on 04/01/2012 bu Kanakshree
                                              dddca_aaf_delegation dad,
                                              dddca_site ds
                                        WHERE     dddca_order_details.order_id =
                                                     drc.orderid(+)
                                              AND dddca_order_details.order_id =
                                                     ds.order_id
                                              AND ds.order_id =
                                                     dad.edca_order_id
                                              AND (   dddca_order_details.mod_status =
                                                         'Y'
                                                   OR dddca_order_details.mod_status
                                                         IS NULL)
                                              AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                       'SCENARIO2',
                                                                                                       'SCENARIO4',
                                                                                                       'SCENARIO5')
                                                   OR dddca_order_details.inflight_scenario_id
                                                         IS NULL)
                                              /*AND
                                             (
                                            (DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid) OR
                                            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) OR
                                            (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid)
                                             )--Changes Made for R20_GSCE_71333 on 04/01/2012*/
                                              AND (   (    dddca_order_details.teamid =
                                                              nteamid
                                                       AND dddca_order_details.delegatedtoteamid
                                                              IS NULL)
                                                   OR dddca_order_details.delegatedtoteamid =
                                                         nteamid)
                                              AND dddca_order_details.teamid =
                                                     nteamid
                                              AND dddca_order_details.creator_person_id
                                                     IS NULL
                                              AND (    UPPER (
                                                          dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                               'ORDER SUBMISSION ERROR')
                                                   AND UPPER (
                                                          dddca_order_details.ordertype) =
                                                          UPPER (vproductname)
                                                   AND (   pass1status <> 'T'
                                                        OR pass1status IS NULL))
                                              AND UPPER (
                                                     dddca_order_details.ort_flag) =
                                                     UPPER (vort_flag)
                                              AND dddca_order_details.delegatereason =
                                                     'AAF Approval Required'
                                              ---Added by Kanakshree For R20_GSCE_71333 to getthe Pending orders only
                                              AND UPPER (ds.service_ownership) =
                                                     UPPER ('REUTERS AND FCE')
                                              AND UPPER (
                                                     dad.finaldelegationtolevel) LIKE
                                                     CASE
                                                        WHEN UPPER (
                                                                authorisation_level) =
                                                                2
                                                        THEN
                                                           UPPER (
                                                              authorisation_level)
                                                        ELSE
                                                           '%' || NULL || '%'
                                                     END
                                              AND dad.isdelegationapproved = 0
                                              AND dad.region = regions
                                              AND dad.finaldelegationrequired =
                                                     1
                                     ORDER BY sort_id, order_id ASC))
                      WHERE rn BETWEEN v_pg_start AND v_pg_end;
               END IF;                        --< IF service_ownership = 'TR'>

               LOOP
                  FETCH v_cursor
                     INTO order_id,
                          order_reference,
                          client_account_id,
                          client_name,
                          received_date,
                          menu_flag,
                          menu_name,
                          sort_id,
                          back_color;

                  EXIT WHEN v_cursor%NOTFOUND;

                  INSERT INTO dddca_aaf_record
                       VALUES (order_id,
                               order_reference,
                               client_account_id,
                               client_name,
                               received_date,
                               menu_flag,
                               menu_name,
                               sort_id,
                               back_color);
               END LOOP;                                   --< FETCH v_cursor>
            --Added by Kanakshree For R18_GSCE_58240
            END LOOP;                            --<FETCH p_o_region_level_so>
         END IF;                               --< IF vproductname = 'SPRING'>
      ELSIF UPPER (vtype) = 'AAFREPORTS'
      THEN
         --Added by Kanakshree For R20_GSCE_71333
         IF vproductname = 'SPRING'
         THEN
            pkg_spring_delegation.pr_get_authorisation_level (
               vuserein,
               p_o_authorise_level,
               p_o_error_flag,
               p_o_error);

            IF p_o_authorise_level = 1
            THEN
               pkg_spring_delegation.pr_get_authorised_regions (
                  vuserein,
                  p_o_authorise_region,
                  p_o_error_flag,
                  p_o_error_message);

               LOOP
                  FETCH p_o_authorise_region INTO authorise_region;

                  EXIT WHEN p_o_authorise_region%NOTFOUND;

                  IF authorise_region <> 'ALL'
                  THEN
                     OPEN v_cursor FOR
                        SELECT *                                --added by Shivani for pagination
                          FROM (SELECT order_id,
                                       order_reference,
                                       client_account_id,
                                       client_name,
                                       received_date,
                                       menu_flag,
                                       menu_name,
                                       sort_id,
                                       back_color,
                                       ROW_NUMBER ()
                                          OVER (ORDER BY order_id DESC)
                                          rn,
                                       COUNT (*) OVER () cnt
                                  FROM (  SELECT DISTINCT
                                                 order_id,
                                                 dd_dca_pk.concat_orderreference (
                                                    dddca_order_details.order_id,
                                                    '/')
                                                    AS order_reference,
                                                 dd_dca_pk.concat_accountid (
                                                    dddca_order_details.order_id,
                                                    '/')
                                                    AS client_account_id,
                                                 dddca_pk_user_profile.concat_clientname (
                                                    dddca_order_details.order_id,
                                                    '/')
                                                    AS client_name,
                                                 TO_CHAR (order_received_date,
                                                          'DD-MON-YYYY')
                                                    received_date,
                                                 vtype AS menu_flag,
                                                 (SELECT menuname
                                                    FROM dddca_menu
                                                   WHERE menuurl =
                                                               'OrderList.aspx?Flag='
                                                            || vtype)
                                                    AS menu_name,
                                                 sort_id,
                                                 dddca_pk_user_profile.get_backcolor_ordertype (
                                                    dddca_order_details.order_id)
                                                    AS back_color
                                            FROM dddca_order_details,
                                                 dddca_reuters_client drc,
                                                 dddca_aaf_delegation dad
                                           WHERE     dddca_order_details.order_id =
                                                        drc.orderid(+)
                                                 AND dad.edca_order_id =
                                                        dddca_order_details.order_id
                                                 AND dddca_order_details.order_id =
                                                        dad.edca_order_id
                                                 AND (   dddca_order_details.mod_status =
                                                            'Y'
                                                      OR dddca_order_details.mod_status
                                                            IS NULL)
                                                 AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                          'SCENARIO2',
                                                                                                          'SCENARIO4',
                                                                                                          'SCENARIO5')
                                                      OR dddca_order_details.inflight_scenario_id
                                                            IS NULL)
                                                 /*AND
                                                (
                                               (DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid) OR
                                               (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) OR
                                               (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid)
                                                ) */
                                                 AND (   (    dddca_order_details.teamid =
                                                                 nteamid
                                                          AND dddca_order_details.delegatedtoteamid
                                                                 IS NULL)
                                                      OR dddca_order_details.delegatedtoteamid =
                                                            nteamid)
                                                 AND dddca_order_details.teamid =
                                                        nteamid
                                                 AND dddca_order_details.creator_person_id
                                                        IS NULL
                                                 AND (    UPPER (
                                                             dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                                  'ORDER SUBMISSION ERROR',
                                                                                                  'REQUEST CREATED')
                                                      AND UPPER (
                                                             dddca_order_details.ordertype) =
                                                             UPPER (
                                                                vproductname)
                                                      AND (   pass1status <>
                                                                 'T'
                                                           OR pass1status
                                                                 IS NULL))
                                                 AND UPPER (
                                                        dddca_order_details.ort_flag) =
                                                        UPPER (vort_flag)
                                                 -- AND dddca_order_details.delegatereason =
                                                 --'AAF Approval Required'
                                                 AND dad.finaldelegationrequired =
                                                        1
                                                 AND dad.finaldelegationtolevel =
                                                        1
                                                 AND dad.region =
                                                        authorise_region
                                        ORDER BY sort_id, order_id ASC))
                         WHERE rn BETWEEN v_pg_start AND v_pg_end;

                     LOOP
                        FETCH v_cursor
                           INTO order_id,
                                order_reference,
                                client_account_id,
                                client_name,
                                received_date,
                                menu_flag,
                                menu_name,
                                sort_id,
                                back_color;

                        EXIT WHEN v_cursor%NOTFOUND;

                        INSERT INTO dddca_aaf_record
                             VALUES (order_id,
                                     order_reference,
                                     client_account_id,
                                     client_name,
                                     received_date,
                                     menu_flag,
                                     menu_name,
                                     sort_id,
                                     back_color);
                     END LOOP;                             --< FETCH v_cursor>
                  END IF;                          --< if REGIONS<>'ALL' THEN>
               END LOOP;                        --<FETCH p_o_authorise_region>
            ELSIF p_o_authorise_level = 2
            THEN
               pkg_spring_delegation.pr_get_authorised_regions (
                  vuserein,
                  p_o_authorise_region,
                  p_o_error_flag,
                  p_o_error_message);

               LOOP
                  FETCH p_o_authorise_region INTO authorise_region;

                  EXIT WHEN p_o_authorise_region%NOTFOUND;

                  IF authorise_region <> 'ALL'
                  THEN
                     OPEN v_cursor FOR
                        SELECT *                               --added by shivani for pagination
                          FROM (SELECT order_id,
                                       order_reference,
                                       client_account_id,
                                       client_name,
                                       received_date,
                                       menu_flag,
                                       menu_name,
                                       sort_id,
                                       back_color,
                                       ROW_NUMBER ()
                                          OVER (ORDER BY order_id DESC)
                                          rn,
                                       COUNT (*) OVER () cnt
                                  FROM (  SELECT DISTINCT
                                                 order_id,
                                                 dd_dca_pk.concat_orderreference (
                                                    dddca_order_details.order_id,
                                                    '/')
                                                    AS order_reference,
                                                 dd_dca_pk.concat_accountid (
                                                    dddca_order_details.order_id,
                                                    '/')
                                                    AS client_account_id,
                                                 dddca_pk_user_profile.concat_clientname (
                                                    dddca_order_details.order_id,
                                                    '/')
                                                    AS client_name,
                                                 TO_CHAR (order_received_date,
                                                          'DD-MON-YYYY')
                                                    received_date,
                                                 vtype AS menu_flag,
                                                 (SELECT menuname
                                                    FROM dddca_menu
                                                   WHERE menuurl =
                                                               'OrderList.aspx?Flag='
                                                            || vtype)
                                                    AS menu_name,
                                                 sort_id,
                                                 dddca_pk_user_profile.get_backcolor_ordertype (
                                                    dddca_order_details.order_id)
                                                    AS back_color
                                            FROM dddca_order_details,
                                                 dddca_reuters_client drc,
                                                 dddca_aaf_delegation dad
                                           WHERE     dddca_order_details.order_id =
                                                        drc.orderid(+)
                                                 AND dad.edca_order_id =
                                                        dddca_order_details.order_id
                                                 AND dddca_order_details.order_id =
                                                        dad.edca_order_id
                                                 AND (   dddca_order_details.mod_status =
                                                            'Y'
                                                      OR dddca_order_details.mod_status
                                                            IS NULL)
                                                 AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                          'SCENARIO2',
                                                                                                          'SCENARIO4',
                                                                                                          'SCENARIO5')
                                                      OR dddca_order_details.inflight_scenario_id
                                                            IS NULL)
                                                 /*AND
                                                (
                                               (DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid) OR
                                               (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO IS NULL) OR
                                               (DDDCA_ORDER_DETAILS.ORDEROWNER = nUserid AND DDDCA_ORDER_DETAILS.DELEGATEDTO = nUserid)
                                                ) */
                                                 AND (   (    dddca_order_details.teamid =
                                                                 nteamid
                                                          AND dddca_order_details.delegatedtoteamid
                                                                 IS NULL)
                                                      OR dddca_order_details.delegatedtoteamid =
                                                            nteamid)
                                                 AND dddca_order_details.teamid =
                                                        nteamid
                                                 AND dddca_order_details.creator_person_id
                                                        IS NULL
                                                 AND (    UPPER (
                                                             dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                                  'ORDER SUBMISSION ERROR',
                                                                                                  'REQUEST CREATED')
                                                      AND UPPER (
                                                             dddca_order_details.ordertype) =
                                                             UPPER (
                                                                vproductname)
                                                      AND (   pass1status <>
                                                                 'T'
                                                           OR pass1status
                                                                 IS NULL))
                                                 AND UPPER (
                                                        dddca_order_details.ort_flag) =
                                                        UPPER (vort_flag)
                                                 -- AND dddca_order_details.delegatereason =
                                                 --'AAF Approval Required'
                                                 AND dad.finaldelegationrequired =
                                                        1
                                                 AND (   dad.finaldelegationtolevel =
                                                            1
                                                      OR dad.finaldelegationtolevel =
                                                            2)
                                                 AND dad.region =
                                                        authorise_region
                                        ORDER BY sort_id, order_id ASC))
                         WHERE rn BETWEEN v_pg_start AND v_pg_end;

                     LOOP
                        FETCH v_cursor
                           INTO order_id,
                                order_reference,
                                client_account_id,
                                client_name,
                                received_date,
                                menu_flag,
                                menu_name,
                                sort_id,
                                back_color;

                        EXIT WHEN v_cursor%NOTFOUND;

                        INSERT INTO dddca_aaf_record
                             VALUES (order_id,
                                     order_reference,
                                     client_account_id,
                                     client_name,
                                     received_date,
                                     menu_flag,
                                     menu_name,
                                     sort_id,
                                     back_color);
                     END LOOP;                             --< FETCH v_cursor>
                  END IF;                                           --<Region>
               END LOOP;                        --<FETCH p_o_authorise_region>
            END IF;                         --< if p_o_authorise_level=2 then>
         END IF;                               --< IF vproductname = 'SPRING'>
      --Added by Kanakshree For R22_GSCE_85005_eDCA_Delegation on 09/07/2012
      ELSIF UPPER (vtype) = 'AAFP'                 --< For AAF Pending Orders>
      THEN
         BEGIN
            OPEN c_cursor FOR
               SELECT *                                  --added by Shivani for pagination
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              client_name,
                              received_date,
                              menu_flag,
                              menu_name,
                              sort_id,
                              back_color,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        /*AND (   (dddca_order_details.delegatedto = nuserid)
                                             OR (    dddca_order_details.orderowner = nuserid
                                                 AND dddca_order_details.delegatedto IS NULL
                                                )
                                             OR (    dddca_order_details.orderowner = nuserid
                                                 AND dddca_order_details.delegatedto = nuserid
                                                )
                                            )*/
                                        AND (   (    dddca_order_details.teamid =
                                                        nteamid
                                                 AND dddca_order_details.delegatedtoteamid
                                                        IS NULL)
                                             OR dddca_order_details.delegatedtoteamid =
                                                   nteamid)
                                        AND dddca_order_details.teamid =
                                               nteamid
                                        AND dddca_order_details.creator_person_id
                                               IS NULL
                                        AND (    UPPER (
                                                    dddca_order_details.orderstatus) IN ('ORIGINATED BY SPRING',
                                                                                         'ORDER SUBMISSION ERROR')
                                             AND UPPER (
                                                    dddca_order_details.ordertype) =
                                                    UPPER (vproductname)
                                             AND (   pass1status <> 'T'
                                                  OR pass1status IS NULL))
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                                        AND dddca_order_details.delegatereason IN ('More Info Required',
                                                                                   'Rejected',
                                                                                   'Approved')
                               ORDER BY sort_id, order_id ASC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := c_cursor;
            vreturnvalue := 'OK';              --< IF vproductname = 'SPRING'>
         END;
      ELSIF UPPER (vtype) = 'AAFD'               --< For AAF Delegated Orders>
      THEN
         BEGIN
            OPEN c_cursor FOR
               SELECT *                           --added for pagination by shivani
                 FROM (SELECT order_id,
                              order_reference,
                              client_account_id,
                              client_name,
                              received_date,
                              menu_flag,
                              menu_name,
                              sort_id,
                              back_color,
                              ROW_NUMBER () OVER (ORDER BY order_id DESC) rn,
                              COUNT (*) OVER () cnt
                         FROM (  SELECT DISTINCT
                                        order_id,
                                        dd_dca_pk.concat_orderreference (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS order_reference,
                                        dd_dca_pk.concat_accountid (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_account_id,
                                        dddca_pk_user_profile.concat_clientname (
                                           dddca_order_details.order_id,
                                           '/')
                                           AS client_name,
                                        TO_CHAR (order_received_date,
                                                 'DD-MON-YYYY')
                                           received_date,
                                        vtype AS menu_flag,
                                        (SELECT menuname
                                           FROM dddca_menu
                                          WHERE menuurl =
                                                      'OrderList.aspx?Flag='
                                                   || vtype)
                                           AS menu_name,
                                        sort_id,
                                        dddca_pk_user_profile.get_backcolor_ordertype (
                                           dddca_order_details.order_id)
                                           AS back_color
                                   FROM dddca_order_details,
                                        dddca_reuters_client drc
                                  WHERE     dddca_order_details.order_id =
                                               drc.orderid(+)
                                        AND (   dddca_order_details.mod_status =
                                                   'Y'
                                             OR dddca_order_details.mod_status
                                                   IS NULL)
                                        AND (   dddca_order_details.inflight_scenario_id NOT IN ('SCENARIO1',
                                                                                                 'SCENARIO2',
                                                                                                 'SCENARIO4',
                                                                                                 'SCENARIO5')
                                             OR dddca_order_details.inflight_scenario_id
                                                   IS NULL)
                                        AND UPPER (ordertype) = 'SPRING'
                                        /*AND dddca_order_details.orderowner = nuserid
                                        AND dddca_order_details.delegatedto <> nuserid
                                        AND (   dddca_order_details.delegatedby IS NOT NULL
                                             OR dddca_order_details.delegatedby = nuserid
                                            )*/
                                        AND dddca_order_details.ordertype =
                                               vproductname
                                        AND dddca_order_details.delegatedbyteamid =
                                               nteamid
                                        AND UPPER (
                                               dddca_order_details.ort_flag) =
                                               UPPER (vort_flag)
                                        AND dddca_order_details.delegatereason =
                                               'AAF Approval Required'
                               ORDER BY order_id DESC))
                WHERE rn BETWEEN v_pg_start AND v_pg_end;

            io_cursor := c_cursor;
            vreturnvalue := 'OK';
         END;
      END IF;                                   --<<If UPPER (vtype) = 'AAF'>>

      IF (UPPER (vtype) = 'AAF' OR UPPER (vtype) = 'AAFREPORTS')
      THEN
         OPEN c_cursor FOR
              SELECT DISTINCT order_id,
                              order_reference,
                              client_account_id,
                              client_name AS "Client Name",
                              received_date,
                              menu_name,
                              menu_flag,
                              sort_id,
                              back_color
                FROM dddca_aaf_record
            ORDER BY sort_id, order_id ASC;

         io_cursor := c_cursor;
         vreturnvalue := 'OK';
      END IF;
   END getaaforders;

   --Ended by Kanakshree For R20_GSCE_71333_Delegation to bind the Order for AAF & AAFReports
   --Added by Kanakshree FOr R23_BTGS_32640_Data Segregation on 12/10/2012
   PROCEDURE get_usergrpuserlist (vuserid      IN     NUMBER,
                                  vorderid     IN     NUMBER,
                                  vteamid      IN     NUMBER,
                                  vortflag     IN     VARCHAR2,
                                  io_cursor       OUT t_cursor,
                                  error_desc      OUT VARCHAR2)
   IS
      vteamcategoryid   NUMBER;
      vcount            NUMBER;
      v_cursor          t_cursor;
      icount            NUMBER;
      newteamcategory   NUMBER;
      vaafdel           NUMBER;
      vcountaafdel      NUMBER;
   BEGIN
      error_desc := 'Here';

      SELECT COUNT (*)
        INTO vcount
        FROM dddca_order_details
       WHERE delegatedto = vuserid AND order_id = vorderid;

      SELECT dddca_team.teamcategoryid
        INTO vteamcategoryid
        FROM dddca_team
       WHERE dddca_team.teamid = vteamid;

      newteamcategory := vteamcategoryid;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = vteamcategoryid
             AND dddca_teamcategory.rceflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 4;
      END IF;

      SELECT COUNT (*)
        INTO icount
        FROM dddca_teamcategory
       WHERE     dddca_teamcategory.teamcategoryid = vteamcategoryid
             AND dddca_teamcategory.springflag = 'Y';

      IF icount > 0
      THEN
         newteamcategory := 3;
      END IF;

      vteamcategoryid := newteamcategory;

      IF vcount = 0
      THEN
         SELECT COUNT (*)
           INTO vcountaafdel
           FROM dddca_aaf_delegation
          WHERE edca_order_id = vorderid;

         IF vcountaafdel > 0
         THEN
            -- HERE
            SELECT finaldelegationrequired
              INTO vaafdel
              FROM dddca_aaf_delegation
             WHERE edca_order_id = vorderid;

            IF vortflag = 'N'
            THEN
               --NON ORT User List for Team -
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE     b.sysuserid = c.orderowner
                            AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSIF vortflag = 'Y'
            THEN
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE     b.sysuserid = c.orderowner
                            AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            END IF;
         ELSE
            IF vortflag = 'N'
            THEN
               --NON ORT User List for Team -
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               /*IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE b.sysuserid = c.orderowner AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE*/
               OPEN v_cursor FOR
                  SELECT DISTINCT
                         dddca_sysuser.sysuserid,
                         dddca_sysuser.username,
                         dddca_sysuser.email
                    FROM dddca_access_control,
                         dddca_role_access,
                         dddca_role_profile,
                         dddca_user_profile,
                         dddca_sysuser
                   WHERE     dddca_access_control.accid =
                                dddca_role_access.accid
                         AND dddca_role_access.roleid =
                                dddca_role_profile.roleid
                         AND dddca_role_profile.profileid =
                                dddca_user_profile.profileid
                         AND dddca_user_profile.sysuserid =
                                dddca_sysuser.sysuserid
                         AND dddca_user_profile.teamid = vteamid
                         AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                         dddca_role_profile.profileid
                                                                    FROM dddca_access_control,
                                                                         dddca_role_access,
                                                                         dddca_role_profile,
                                                                         dddca_user_profile,
                                                                         dddca_sysuser
                                                                   WHERE     dddca_access_control.accid =
                                                                                dddca_role_access.accid
                                                                         AND dddca_role_access.roleid =
                                                                                dddca_role_profile.roleid
                                                                         AND dddca_role_profile.profileid =
                                                                                dddca_user_profile.profileid
                                                                         AND dddca_user_profile.sysuserid =
                                                                                dddca_sysuser.sysuserid
                                                                         AND UPPER (
                                                                                accessname) =
                                                                                UPPER (
                                                                                   'Access ORT Countries'))
                         AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                           FROM dddca_user_usergroup_mapping dum,
                                                                dddca_cust_usergroup_mapping dcm
                                                          WHERE     dum.user_group_id =
                                                                       dcm.user_group_id
                                                                AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                          FROM dddca_customer dc
                                                                                         WHERE dc.order_id =
                                                                                                  vorderid))
                         AND dddca_sysuser.sysuserid <> vuserid
                  UNION
                  SELECT NULL AS sysuserid,
                         'Others' AS username,
                         NULL AS email
                    FROM DUAL
                  ORDER BY username;
            -- END IF;
            ELSIF vortflag = 'Y'
            THEN
               ---Added by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               /*IF vaafdel = 1
               THEN
                  OPEN v_cursor FOR
                     SELECT b.sysuserid, b.username, b.email
                       FROM dddca_sysuser b, dddca_order_details c
                      WHERE b.sysuserid = c.orderowner AND c.order_id = vorderid;
               ---Ended by Kanakshree FOr R20_GSCE_71333 on 17/04/2012 for AAF Delegation orders
               ELSE*/
               --ORT User List for Team -
               OPEN v_cursor FOR
                  SELECT DISTINCT
                         dddca_sysuser.sysuserid,
                         dddca_sysuser.username,
                         dddca_sysuser.email
                    FROM dddca_access_control,
                         dddca_role_access,
                         dddca_role_profile,
                         dddca_user_profile,
                         dddca_sysuser
                   WHERE     dddca_access_control.accid =
                                dddca_role_access.accid
                         AND dddca_role_access.roleid =
                                dddca_role_profile.roleid
                         AND dddca_role_profile.profileid =
                                dddca_user_profile.profileid
                         AND dddca_user_profile.sysuserid =
                                dddca_sysuser.sysuserid
                         AND UPPER (accessname) =
                                UPPER ('Access ORT Countries')
                         AND dddca_user_profile.teamid = vteamid
                         AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                           FROM dddca_user_usergroup_mapping dum,
                                                                dddca_cust_usergroup_mapping dcm
                                                          WHERE     dum.user_group_id =
                                                                       dcm.user_group_id
                                                                AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                          FROM dddca_customer dc
                                                                                         WHERE dc.order_id =
                                                                                                  vorderid))
                         AND dddca_sysuser.sysuserid <> vuserid
                  UNION
                  SELECT NULL AS sysuserid,
                         'Others' AS username,
                         NULL AS email
                    FROM DUAL
                  ORDER BY username;
            --END IF;
            END IF;
         END IF;
      ELSIF vcount > 0
      THEN
         IF vteamcategoryid = 1
         THEN
            SELECT COUNT (*)
              INTO vcount
              FROM dddca_order_details
             WHERE order_id = vorderid AND delegatedto = creator_person_id;

            IF vcount > 0
            THEN                                      --Delegated back to self
               --Display all users
               ---HERE
               IF vortflag = 'N'
               THEN
                  --NON ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               ELSIF vortflag = 'Y'
               THEN
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSE
               OPEN v_cursor FOR
                  SELECT b.sysuserid, b.username, b.email
                    FROM dddca_sysuser b, dddca_order_details c
                   WHERE     b.sysuserid = c.creator_person_id
                         AND c.order_id = vorderid;
            END IF;
         ELSIF vteamcategoryid = 2 OR vteamcategoryid = 3
         THEN
            SELECT COUNT (*)
              INTO vcount
              FROM dddca_order_details
             WHERE order_id = vorderid AND delegatedto = orderowner;

            IF vcount > 0
            THEN                                      --Delegated back to self
               --Display all users
               ---HERE
               IF vortflag = 'N'
               THEN
                  --NON ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               ELSIF vortflag = 'Y'
               THEN
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSE
               OPEN v_cursor FOR
                  SELECT b.sysuserid, b.username, b.email
                    FROM dddca_sysuser b, dddca_order_details c
                   WHERE b.sysuserid = c.orderowner AND c.order_id = vorderid;
            END IF;
         ELSIF vteamcategoryid = 5 OR vteamcategoryid = 4
         THEN
            SELECT COUNT (*)
              INTO vcount
              FROM dddca_order_details
             WHERE order_id = vorderid AND delegatedto = rceowner;

            IF vcount > 0
            THEN                                      --Delegated back to self
               --Display all users
               ---HERE
               IF vortflag = 'N'
               THEN
                  --NON ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_role_profile.profileid NOT IN (SELECT DISTINCT
                                                                            dddca_role_profile.profileid
                                                                       FROM dddca_access_control,
                                                                            dddca_role_access,
                                                                            dddca_role_profile,
                                                                            dddca_user_profile,
                                                                            dddca_sysuser
                                                                      WHERE     dddca_access_control.accid =
                                                                                   dddca_role_access.accid
                                                                            AND dddca_role_access.roleid =
                                                                                   dddca_role_profile.roleid
                                                                            AND dddca_role_profile.profileid =
                                                                                   dddca_user_profile.profileid
                                                                            AND dddca_user_profile.sysuserid =
                                                                                   dddca_sysuser.sysuserid
                                                                            AND UPPER (
                                                                                   accessname) =
                                                                                   UPPER (
                                                                                      'Access ORT Countries'))
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               ELSIF vortflag = 'Y'
               THEN
                  --ORT User List for Team -
                  OPEN v_cursor FOR
                     SELECT DISTINCT
                            dddca_sysuser.sysuserid,
                            dddca_sysuser.username,
                            dddca_sysuser.email
                       FROM dddca_access_control,
                            dddca_role_access,
                            dddca_role_profile,
                            dddca_user_profile,
                            dddca_sysuser
                      WHERE     dddca_access_control.accid =
                                   dddca_role_access.accid
                            AND dddca_role_access.roleid =
                                   dddca_role_profile.roleid
                            AND dddca_role_profile.profileid =
                                   dddca_user_profile.profileid
                            AND dddca_user_profile.sysuserid =
                                   dddca_sysuser.sysuserid
                            AND UPPER (accessname) =
                                   UPPER ('Access ORT Countries')
                            AND dddca_user_profile.teamid = vteamid
                            AND dddca_sysuser.sysuserid IN (SELECT dum.sysuserid
                                                              FROM dddca_user_usergroup_mapping dum,
                                                                   dddca_cust_usergroup_mapping dcm
                                                             WHERE     dum.user_group_id =
                                                                          dcm.user_group_id
                                                                   AND dcm.customer_id IN (SELECT dc.mastercustomerid
                                                                                             FROM dddca_customer dc
                                                                                            WHERE dc.order_id =
                                                                                                     vorderid))
                            AND dddca_sysuser.sysuserid <> vuserid
                     UNION
                     SELECT NULL AS sysuserid,
                            'Others' AS username,
                            NULL AS email
                       FROM DUAL
                     ORDER BY username;
               END IF;
            ELSE
               OPEN v_cursor FOR
                  SELECT b.sysuserid, b.username, b.email
                    FROM dddca_sysuser b, dddca_order_details c
                   WHERE b.sysuserid = c.rceowner AND c.order_id = vorderid;
            END IF;
         END IF;
      END IF;

      io_cursor := v_cursor;
   EXCEPTION
      WHEN OTHERS
      THEN
         error_desc := SQLERRM;
   END get_usergrpuserlist;
--Ended by Kanakshree FOr R23_BTGS_32640_Data Segregation on 12/10/2012
END dddca_pk_user_profile;
/