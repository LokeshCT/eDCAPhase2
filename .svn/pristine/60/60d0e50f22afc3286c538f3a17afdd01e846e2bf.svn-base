CREATE OR REPLACE PROCEDURE MDSLD.getspecificOrders (
   in_Order_id          IN     NUMBER,
   in_Orderreference    IN     VARCHAR2,
   in_Clientaccountid   IN     VARCHAR2,
   out_cursor        OUT    SYS_REFCURSOR,
   out_VRETURNVALUE  OUT    VARCHAR2)
AS
  
   /******************************************************************************
    Ver    Date        Author      Description
   -----  ----------  ----------  ------------------------------------
   1.0   05/10/2015   Shivani J   Introduced specific search to list the orders detail in UI
  ******************************************************************************/

  l_order_reference   VARCHAR2 (200);
  l_client_id         VARCHAR2 (200);
BEGIN
   IF in_Order_id IS NOT NULL
   THEN
      OPEN out_cursor FOR
           SELECT DISTINCT
                  order_id,
                  dd_dca_pk.concat_orderreference (
                     dddca_order_details.order_id,
                     '/')
                     AS order_reference,
                  dd_dca_pk.concat_accountid (dddca_order_details.order_id,
                                              '/')
                     AS client_account_id,
                  dddca_pk_user_profile.concat_clientname (
                     dddca_order_details.order_id,
                     '/')
                     AS Client_Name,
                  TO_CHAR (order_received_date, 'DD-MON-YYYY') received_date,
                  sort_id,
                  dddca_pk_user_profile.get_backcolor_ordertype (
                     dddca_order_details.order_id)
                     AS back_color
             FROM dddca_order_details, dddca_reuters_client drc
            WHERE     dddca_order_details.order_id = drc.orderid(+)
                  AND dddca_order_details.order_id = in_Order_id
         ORDER BY sort_id, order_id ASC;
   --dbms_output.put_line(order_id);
      --io_cursor := v_cursor;
      out_VRETURNVALUE := 'OK';
      --Close io_cursor;
   ELSIF in_Orderreference IS NOT NULL
   THEN
      SELECT order_id
        INTO l_order_reference
        FROM dddca_externalsystem
       WHERE externalidentifier =
                (SELECT REGEXP_SUBSTR (in_Orderreference, '(\S*)') FROM DUAL);

      OPEN out_cursor FOR
           SELECT DISTINCT
                  order_id,
                  dd_dca_pk.concat_orderreference (
                     dddca_order_details.order_id,
                     '/')
                     AS order_reference,
                  dd_dca_pk.concat_accountid (dddca_order_details.order_id,
                                              '/')
                     AS client_account_id,
                  dddca_pk_user_profile.concat_clientname (
                     dddca_order_details.order_id,
                     '/')
                     AS Client_Name,
                  TO_CHAR (order_received_date, 'DD-MON-YYYY') received_date,
                  sort_id,
                  dddca_pk_user_profile.get_backcolor_ordertype (
                     dddca_order_details.order_id)
                     AS back_color
             FROM dddca_order_details, dddca_reuters_client drc
            WHERE     dddca_order_details.order_id = drc.orderid(+)
                  AND dddca_order_details.order_id = l_order_reference
         ORDER BY sort_id, order_id ASC;

     -- io_cursor := v_cursor;
      out_VRETURNVALUE := 'OK';
       --Close io_cursor;
   ELSIF in_Clientaccountid IS NOT NULL
   THEN
      SELECT orderid
        INTO l_client_id
        FROM dddca_reuters_client
       WHERE client_account_id =
                (SELECT REGEXP_SUBSTR (in_Clientaccountid, '(\S*)')
                           AS client_account_id
                   FROM DUAL);

      OPEN out_cursor FOR
           SELECT DISTINCT
                  order_id,
                  dd_dca_pk.concat_orderreference (
                     dddca_order_details.order_id,
                     '/')
                     AS order_reference,
                  dd_dca_pk.concat_accountid (dddca_order_details.order_id,
                                              '/')
                     AS client_account_id,
                  dddca_pk_user_profile.concat_clientname (
                     dddca_order_details.order_id,
                     '/')
                     AS Client_Name,
                  TO_CHAR (order_received_date, 'DD-MON-YYYY') received_date,
                  sort_id,
                  dddca_pk_user_profile.get_backcolor_ordertype (
                     dddca_order_details.order_id)
                     AS back_color
             FROM dddca_order_details, dddca_reuters_client drc
            WHERE     dddca_order_details.order_id = drc.orderid(+)
                  AND dddca_order_details.order_id = l_client_id
         ORDER BY sort_id, order_id ASC;

      --io_cursor := v_cursor;
      out_VRETURNVALUE := 'OK';
   ELSE     
    out_VRETURNVALUE := 'No values entered in the text boxes';
   END IF;

END getspecificOrders;
/