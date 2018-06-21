/* Formatted on 14/10/2015 13:47:13 (QP5 v5.252.13127.32847) */
CREATE OR REPLACE PACKAGE MDSLD.PK_MOD_GENERAL
AS
   TYPE t_cursor IS REF CURSOR;


   PROCEDURE dca_convert_order (p_i_scenario_id         IN     VARCHAR2,
                                p_i_inflight_order_id   IN     NUMBER,
                                p_i_earlier_order_id    IN     NUMBER,
                                p_i_user_id             IN     NUMBER,
                                p_i_team_category_id    IN     NUMBER,
                                p_o_email_id               OUT VARCHAR2,
                                p_o_error_desc             OUT VARCHAR2,
                                p_i_team_id             IN     NUMBER);

   PROCEDURE dca_get_earlier_order (p_i_inflight_order_id   IN     NUMBER,
                                    p_i_order_type          IN     VARCHAR2,
                                    p_o_earlier_order_id       OUT NUMBER,
                                    p_o_error_desc             OUT VARCHAR2);

   PROCEDURE GetInventoryOrders (pUserid      IN     NUMBER,
                                 pTeamId      IN     NUMBER,
                                 pORT_FLAG    IN     VARCHAR2,
                                 V_PG_START   IN     NUMBER,-- added the attribute for pagination by shivani
                                 v_pg_end     IN     NUMBER,-- added the attribute for pagination by shivani
                                 io_cursor       OUT t_cursor);

   PROCEDURE GetFailedInventoryOrders (pUserid     IN     NUMBER,
                                       pTeamId     IN     NUMBER,
                                       pORT_FLAG   IN     VARCHAR2,
                                       io_cursor      OUT t_cursor);

   --Comment BEGIN by Sujit Kumar Sahu on 05-Jun-2009 for fixing Live Defect Id BF381987, BF377926 - R894
   PROCEDURE GET_RESILIENCE_ORDERTYPE (vOrderId       IN     NUMBER,
                                       vIResilience      OUT VARCHAR2,
                                       vIOrderType       OUT VARCHAR2,
                                       vErrorDesc        OUT VARCHAR2);

   --Comment by Sujit Kumar Sahu on 28-Jan-2010 for code merge from R903
   --Comment by Sujit Kumar Sahu on 27-Jan-2010 for CR JL2.3 - R903
   PROCEDURE IS_LEASED_LONGLINE (p_Amend_order_id           IN     NUMBER,
                                 p_INV_order_id             IN     NUMBER,
                                 p_new_service_instanceid   IN     NUMBER,
                                 p_old_service_instanceid   IN     NUMBER,
                                 p_ReturnValue                 OUT VARCHAR2);

   --Comment BEGIN by Sujit Kumar Sahu on 24-Feb-2010 for Logical and RCD Amends related to CR260210 and CR260211 - R903.x
   PROCEDURE GET_INV_DIFF (p_i_orderid             IN     NUMBER,
                           p_i_seviceinstance      IN     VARCHAR2,
                           p_i_serviceinstanceid   IN     NUMBER,
                           p_o_error_description      OUT VARCHAR2,
                           p_i_CPESeqID            IN     NUMBER);

   PROCEDURE GET_INV_SERVICEINSTANCEID (
      p_i_orderid             IN     NUMBER,
      p_i_seviceinstance      IN     VARCHAR2,
      p_o_serviceinstanceid      OUT NUMBER,
      p_o_error_description      OUT VARCHAR2);

   PROCEDURE IS_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                            p_inv_serviceinstanceid   IN     NUMBER,
                            p_o_errordescription         OUT VARCHAR2,
                            p_i_SeqId                 IN     NUMBER);


   PROCEDURE GET_CARDDESCRIPTION (p_i_serviceinstanceid   IN     NUMBER,
                                  p_i_cardposition        IN     VARCHAR2,
                                  p_o_carddescription        OUT VARCHAR2,
                                  p_o_errordescription       OUT VARCHAR2,
                                  p_i_SeqId               IN     NUMBER);



   PROCEDURE IS_CARDNOTEXISTS (p_i_serviceinstanceid   IN     NUMBER,
                               p_o_errordescription       OUT VARCHAR2,
                               p_i_SeqId               IN     NUMBER);

   --added by Kanakshree for R16_GSCE 62414
   PROCEDURE IS_CPE_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                                p_inv_serviceinstanceid   IN     NUMBER,
                                p_o_errordescription         OUT VARCHAR2,
                                p_i_SeqId                 IN     NUMBER);

   PROCEDURE IS_LAN_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                                p_inv_serviceinstanceid   IN     NUMBER,
                                p_o_errordescription         OUT VARCHAR2,
                                p_i_SeqId                 IN     NUMBER);

   --Comment END by Sujit Kumar Sahu on 24-Feb-2010 for Logical and RCD Amends related to CR260210 and CR260211 - R903.x

   --Comment BEGIN by Sujit Kumar Sahu on 03-May-2010 for fixing SIT Defect Id 70200 - R011
   PROCEDURE check_get_hecpe (v_cpe_name            IN     VARCHAR2,
                              v_cpe_series             OUT VARCHAR2,
                              v_error_description      OUT VARCHAR2);

   PROCEDURE check_hecpe_count (v_order_id            IN     NUMBER,
                                v_primary_count          OUT NUMBER,
                                v_secondary_count        OUT NUMBER,
                                v_error_description      OUT VARCHAR2);

   PROCEDURE get_sec_cpe (v_order_id            IN     NUMBER,
                          v_sec_cpe                OUT VARCHAR2,
                          v_error_description      OUT VARCHAR2);

   --Comment END by Sujit Kumar Sahu on 03-May-2010 for fixing SIT Defect Id 70200 - R011

   --Comment BEGIN by Sujit Kumar Sahu on 15-Jun-2010 for fixing Live Defect HSBC - Service Orders for 8CS and HDC  ----  7304 Issue - R011
   PROCEDURE check_mod_invcpe (p_i_order_id            IN     NUMBER,
                               p_i_service_instance    IN     VARCHAR2,
                               p_o_error_description      OUT VARCHAR2,
                               p_i_cpeSeqID            IN     NUMBER);



   PROCEDURE GetAuthCPEData (ServiInst        NUMBER,
                             cpeseqid         NUMBER,
                             parentpage       VARCHAR2,
                             io_cursor    OUT t_cursor);

   --added by Kanakshree for R16_GSCE 62414
   PROCEDURE GetAuthLANData (ServiInst        NUMBER,
                             cpeseqid         NUMBER,
                             parentpage       VARCHAR2,
                             io_cursor    OUT t_cursor);

   --added by Kanakshree for R16_GSCE 62414
   PROCEDURE GET_LAN_SWITCH (p_i_servInstID   IN     NUMBER,
                             p_i_lanSeqId     IN     NUMBER,
                             p_i_lanSwitch       OUT VARCHAR2,
                             p_i_lanUsage        OUT VARCHAR2);

   PROCEDURE GET_INTERFACE_TYPE (p_i_servInstID     IN     NUMBER,
                                 p_o_InerfaceType      OUT VARCHAR2);

   PROCEDURE IS_EXTPORT_NOTEXISTS (p_i_serviceinstanceid   IN     NUMBER,
                                   p_o_errordescription       OUT VARCHAR2,
                                   p_i_SeqId               IN     NUMBER);

   PROCEDURE IS_EXTSPORT_NOTEXISTS (p_i_serviceinstanceid   IN     NUMBER,
                                    p_o_errordescription       OUT VARCHAR2,
                                    p_i_SeqId               IN     NUMBER);

   PROCEDURE GET_PortType (p_i_serviceinstanceid   IN     NUMBER,
                           p_i_elementtype         IN     VARCHAR2,
                           p_o_porttype               OUT VARCHAR2,
                           p_o_errordescription       OUT VARCHAR2,
                           p_i_elementname         IN     VARCHAR2, --Defect 4923
                           p_i_SeqId               IN     NUMBER);

   PROCEDURE GET_LPortType (p_i_serviceinstanceid   IN     NUMBER,
                            p_i_elementtype         IN     VARCHAR2,
                            p_o_porttype               OUT VARCHAR2,
                            p_o_errordescription       OUT VARCHAR2,
                            p_i_elementname         IN     VARCHAR2, --Defect 4923
                            p_i_SeqId               IN     NUMBER);


   PROCEDURE GET_CPE_CARD (p_i_serviceinstanceid   IN     NUMBER,
                           p_i_SeqId               IN     NUMBER,
                           p_o_error_flag             OUT VARCHAR2,
                           p_o_error_message          OUT VARCHAR2,
                           p_o_extendedcards          OUT SYS_REFCURSOR);

   PROCEDURE GET_LAN_CARD (p_i_serviceinstanceid   IN     NUMBER,
                           p_i_SeqId               IN     NUMBER,
                           p_o_error_flag             OUT VARCHAR2,
                           p_o_error_message          OUT VARCHAR2,
                           p_o_extendedcards          OUT SYS_REFCURSOR);


   PROCEDURE check_mod_invdevice (p_i_order_id            IN     NUMBER,
                                  p_i_service_instance    IN     VARCHAR2,
                                  p_o_error_description      OUT VARCHAR2,
                                  p_i_cpeSeqID            IN     NUMBER);

   PROCEDURE GET_LAN_CARD_DESC (p_i_servInstID   IN     NUMBER,
                                p_i_lanSeqID     IN     NUMBER,
                                p_i_cardPos      IN     VARCHAR2,
                                p_o_cardDesc        OUT VARCHAR2,
                                p_o_errDesc         OUT VARCHAR2);

   PROCEDURE IS_DEVICE_CARDNOTEXISTS (
      p_i_serviceinstanceid   IN     NUMBER,
      p_o_errordescription       OUT VARCHAR2,
      p_i_SeqId               IN     NUMBER);

   PROCEDURE GET_DEVICE_CARDDESCRIPTION (
      p_i_serviceinstanceid   IN     NUMBER,
      p_i_cardposition        IN     VARCHAR2,
      p_o_carddescription        OUT VARCHAR2,
      p_o_errordescription       OUT VARCHAR2,
      p_i_SeqId               IN     NUMBER);

   PROCEDURE IS_DEVICE_CARDEXISTS (p_i_serviceinstanceid     IN     NUMBER,
                                   p_inv_serviceinstanceid   IN     NUMBER,
                                   p_o_errordescription         OUT VARCHAR2,
                                   p_i_SeqId                 IN     NUMBER);
END PK_MOD_GENERAL;
/