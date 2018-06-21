CREATE OR REPLACE PACKAGE MDSLD.DDDCA_PK_USER_PROFILE
IS
  /******************************************************************************
  NAME:       DDDCA_PK_USER_PROFILE
  PURPOSE:    To calculate the desired information.
  REVISIONS:
  Ver        Date        Author           Description
  ---------  ----------  ---------------  ------------------------------------
  1.0        3/17/2006             1. Created this package.
  PARAMETERS:
  INPUT:
  OUTPUT:
  RETURNED VALUE:
  CALLED BY:
  CALLS:
  EXAMPLE USE:     NUMBER := DDDCA_PK_USER_PROFILE.MyFuncName(Number);
  DDDCA_PK_USER_PROFILE.MyProcName(Number, Varchar2);
  ASSUMPTIONS:
  LIMITATIONS:
  ALGORITHM:
  NOTES:
  Here is the complete list of automatically available Auto Replace Keywords:
  Object Name:     DDDCA_PK_USER_PROFILE or DDDCA_PK_USER_PROFILE
  Sysdate:         3/17/2006
  Date/Time:       3/17/2006 12:12:18 PM
  Date:            3/17/2006
  Time:            12:12:18 PM
  Username:         (set in TOAD Options, Procedure Editor)
  Table Name:      %TableName% (set in the Create New Procedure dialog)
  
  2.0       18/08/2015  Mugilan S   Introduced two variables (v_pg_start & v_pg_end) in GETORDERS for pagination.
  ******************************************************************************/
TYPE t_cursor 
IS
  REF
  CURSOR ;
  PROCEDURE DDDCA_sp_CreateTeamCategory(
      vTeamName       IN VARCHAR2,
      vRCEFlag        IN VARCHAR2,
      vSubmissionFLAG IN VARCHAR2,
      vSpringFlag     IN VARCHAR2,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE GetAccessControlList1(
      io_cursor OUT t_cursor);
  PROCEDURE GetAccessControlList2(
      vRoleId IN NUMBER,
      io_cursor OUT t_cursor);
  PROCEDURE DDDCA_sp_iRole(
      vRoleName IN VARCHAR2,
      nTeamId   IN NUMBER,
      vRoleId OUT NUMBER,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE DDDCA_sp_uRole(
      nRoleId   IN NUMBER,
      vRoleName IN VARCHAR2,
      nTeamId   IN NUMBER,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE DDDCA_sp_iRoleAccess(
      vRoleId   IN VARCHAR2,
      vAccessId IN NUMBER,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE GetRoleNameList(
      nTeamId          IN NUMBER,
      vLocalGlobalFlag IN CHAR,
      io_cursor OUT t_cursor);
  PROCEDURE DDDCA_sp_dRoleAccess(
      vRoleId IN VARCHAR2,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE GetCountryList(
      io_cursor OUT t_cursor);
  PROCEDURE GetCountryListByProduct(
      vProduct IN VARCHAR2,
      io_cursor OUT t_cursor);
    --PROCEDURE DDDCA_sp_uCountry (vCountry_Name in varchar2, vORTFlag in varchar2, ERROR_DESC OUT varchar2);
  PROCEDURE DDDCA_sp_SaveReason(
      vFlag           IN VARCHAR2,
      vUserId         IN NUMBER,
      vReason         IN VARCHAR2,
      vOrderId        IN NUMBER,
      vTeamCategoryId IN NUMBER,
      vEmailId OUT VARCHAR2,
      ERROR_DESC OUT VARCHAR2,
      vTeamId IN NUMBER);
  PROCEDURE GetAdminEmailList(
      vOrderId IN NUMBER,
      io_cursor OUT t_cursor,
      vTeamId IN NUMBER);
    --  PROCEDURE GetUserList(vUserId in Number, vOrderId in Number, vTeamCategoryId in Number, io_cursor OUT t_cursor, ERROR_DESC OUT varchar2);
  PROCEDURE GetUserList(
      vUserId  IN NUMBER,
      vOrderId IN NUMBER,
      vTEAMID  IN NUMBER,
      vORTFlag IN VARCHAR2,
      io_cursor OUT t_cursor,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE DDDCA_sp_SaveDeligation(
      vUserId          IN NUMBER,
      vDelegateUserId  IN NUMBER,
      vReason          IN VARCHAR2,
      vOrderId         IN NUMBER,
      vTeamCategoryId  IN NUMBER,
      nDelegatedToTeam IN NUMBER,
      nDelegatedByTeam IN NUMBER,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE GetTeamCategoryList(
      io_cursor OUT t_cursor);
  PROCEDURE GetRoleNameList2(
      vRoleId IN NUMBER,
      io_cursor OUT t_cursor);
  PROCEDURE GetTeamCategoryById(
      vTeamCategoryId IN NUMBER,
      io_cursor OUT t_cursor,
      ERROR_DESC OUT VARCHAR2);
  PROCEDURE GetTeams(
      vType IN CHAR,
      io_cursor OUT t_cursor,
      vReturnValue OUT VARCHAR2);
  PROCEDURE GetAllProducts(
      io_cursor OUT t_cursor,
      vReturnValue OUT VARCHAR2);
    --PROCEDURE DDDCA_sp_UpdateTeamCategory ( vTeamCategoryId in number, vTeamCategoryName in varchar2,vRCEFlag IN VARCHAR2,vSubmissionFLAG IN VARCHAR2, ERROR_DESC OUT varchar2);
    --  procedure AddUpdateSubmissionQueue(nTeamId in Number, nProductId in  number,nQueueId in  number, vEmail in varchar2,nDefaultOwner in number,vReturnValue out varchar2);
  PROCEDURE AddUpdateSubmissionQueue(
      nTeamId             IN NUMBER,
      nProductId          IN NUMBER,
      nQueueId            IN NUMBER,
      vEmail              IN VARCHAR2,
      nDefaultOwner       IN NUMBER,
      nRejectTeam         IN NUMBER,
      nRejectDefaultOwner IN NUMBER,
      vReturnValue OUT VARCHAR2);
  PROCEDURE GetSubmissionQueue(
      io_cursor OUT t_cursor);
  PROCEDURE GetSubmissionQueueDetail(
      vSubmissionQId IN NUMBER,
      io_cursor OUT t_cursor);
  PROCEDURE GETORDERS(
      NUSERID         IN NUMBER,
      NTEAMID         IN NUMBER,
      VPRODUCTNAME    IN VARCHAR2,
      VTYPE           IN VARCHAR,
      NTEAMCATEGORYID IN OUT NUMBER,
      VORT_FLAG       IN VARCHAR,
      NPROFILEID      IN NUMBER,
      V_PG_START      IN NUMBER,
      V_PG_END        in number,    
      io_cursor OUT t_cursor,
      vReturnValue OUT VARCHAR2);
  PROCEDURE GetUserProfile(
      nUserId IN NUMBER,
      io_cursor OUT t_cursor);
  PROCEDURE SaveUserDefaultProfile(
      nUserId    IN NUMBER,
      nProfileId IN NUMBER,
      nTeamId    IN NUMBER,
      vReturnValue OUT VARCHAR2);
  PROCEDURE GetSubmission_Queue(
      io_cursor OUT t_cursor);
  PROCEDURE GetCountryAccessModel(
      io_cursor OUT t_cursor);
  PROCEDURE AdvanceSearch(
      vProduct     IN VARCHAR2,
      nFrom        IN VARCHAR2,
      nTo          IN NUMBER,
      vOriginator  IN VARCHAR2,
      nTeamId      IN NUMBER,
      vOrderStatus IN VARCHAR2,
      io_cursor OUT t_cursor);
  PROCEDURE OrderUploadStatus(
      nOrderId IN NUMBER,
      sMsgType OUT VARCHAR2,
      sMsgDesc OUT VARCHAR2,
      sRefresh OUT VARCHAR);
    --CR:210980: Single Site Rejection
  PROCEDURE DCA_COPY_REJECTED_SITES(
      P_I_PARENT_ORDER_ID IN NUMBER,
      P_I_CHILD_ORDER_ID  IN NUMBER,
      P_O_STATUS OUT VARCHAR2);
    --CR:226471 : Order Save Functionality
  PROCEDURE GET_ORDERSTATUS(
      P_ORDER_ID IN NUMBER,
      P_ORDER_STATUS OUT VARCHAR2);
    --Comment by Sujit Kumar Sahu on 24-Dec-2008 for enhance performance enhancement of query
    --query that are used in Front End is to be called from back end once for comparision of value
  PROCEDURE GET_INTERNALORDERTYPE(
      vOrderId IN NUMBER,
      vIOrderTypeUnion OUT VARCHAR2,
      vIOrderType OUT VARCHAR2,
      vErrorDesc OUT VARCHAR2);
    --Comment BEGIN by Sujit Kumar Sahu on 08-Jan-2009 for enhance performance of Search In All Order in Orderlist Page
  PROCEDURE GetSearchInAllQueueOrderId(
      nUserid         IN NUMBER,
      nTeamId         IN NUMBER,
      vProductName    IN VARCHAR2,
      vType           IN VARCHAR,
      nTeamCategoryId IN OUT NUMBER,
      vORT_FLAG       IN VARCHAR,
      nProfileId      IN NUMBER,
      io_cursor OUT t_cursor,
      vReturnValue OUT VARCHAR2,
      vSearchAllOrders IN NUMBER);
  PROCEDURE GetSearchAllQOrderRef(
      nUserid         IN NUMBER,
      nTeamId         IN NUMBER,
      vProductName    IN VARCHAR2,
      vType           IN VARCHAR,
      nTeamCategoryId IN OUT NUMBER,
      vORT_FLAG       IN VARCHAR,
      nProfileId      IN NUMBER,
      io_cursor OUT t_cursor,
      vReturnValue OUT VARCHAR2,
      vSearchAllOrders IN VARCHAR2);
  PROCEDURE GetSearchAllQClientAccId(
      nUserid         IN NUMBER,
      nTeamId         IN NUMBER,
      vProductName    IN VARCHAR2,
      vType           IN VARCHAR,
      nTeamCategoryId IN OUT NUMBER,
      vORT_FLAG       IN VARCHAR,
      nProfileId      IN NUMBER,
      io_cursor OUT t_cursor,
      vReturnValue OUT VARCHAR2,
      vSearchAllOrders IN VARCHAR2);
    --Comment END by Sujit Kumar Sahu on 08-Jan-2009 for enhance performance of Search In All Order in Orderlist Page
    --Comment by Sujit Kumar Sahu on 20-Apr-2009 for enhance performance of Search Page
    FUNCTION GET_BACKCOLOR_ORDERTYPE(
        vOrderId IN NUMBER)
      RETURN VARCHAR2;
    FUNCTION CONCAT_CLIENTNAME(
        P_ORDER_ID NUMBER,
        DELIMITION VARCHAR2)
      RETURN VARCHAR2;
      ---Added by Kanakshree For R20_GSCE_71333_Delegation to bind the Order for AAF & AAFReports
    PROCEDURE getAAForders(
        nuserid         IN NUMBER,
        nteamid         IN NUMBER,
        vproductname    IN VARCHAR2,
        vtype           IN VARCHAR,
        nteamcategoryid IN OUT NUMBER,
        vort_flag       IN VARCHAR,
        nprofileid      IN NUMBER,
         V_PG_START      IN NUMBER, -- added the attribute for pagination by shivani
      v_pg_end        IN NUMBER,    -- added the attribute for pagination by shivani
        io_cursor OUT t_cursor,
        vreturnvalue OUT VARCHAR2 );
      ---Ended by Kanakshree For R20_GSCE_71333_Delegation to bind the Order for AAF & AAFReports
    PROCEDURE getrolelist(
        io_cursor OUT t_cursor);
    PROCEDURE get_usergrpuserlist(
        vuserid  IN NUMBER,
        vorderid IN NUMBER,
        vteamid  IN NUMBER,
        vortflag IN VARCHAR2,
        io_cursor OUT t_cursor,
        error_desc OUT VARCHAR2 );
    END DDDCA_PK_USER_PROFILE;
/