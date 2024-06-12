const api = '/api'
const auth = api + '/auth'
const student = api + '/student'
const admin = api + '/admin'
const club = api + '/club'
const activity = api + '/activity'
const file = api + '/file'
const base = {
    /*——————————————认证中心——————————————*/
    login: auth + '/login', // 登录
    register: auth + '/register', // 注册
    postAdminLogin: auth + '/adminLogin', //管理员登录
    verifyCode: auth + '/verifyCode', // 获取验证码
    getImageVerifyCode: auth + '/getImageVerifyCode', // 获取图片验证码
    getEnumList: auth + '/getEnumList', // 获取枚举列表


    /*——————————————学生——————————————*/
    // get
    userInfo: student + '/userInfo', // 获取用户信息

    // post

    userInfoUpdate: student + '/userInfoUpdate', // 更新用户信息
    backBoneEvaluate: student + '/backBoneEvaluate', // 提交骨干评优申请


    /*———————————————管理员——————————————*/
    // get
    adminInfo: admin + '/adminInfo',//获取管理员信息
    backBoneEvaluations: admin + '/backBoneEvaluations',  // 获取所有的骨干评优信息
    clubAnnuals: admin + '/clubAnnuals',    // 获取所有社团年审
    clubEvaluations: admin + '/clubEvaluations', // 获取所有社团评优
    myClubAnnualExamData: admin + '/myClubAnnualExamData',//获取特定社团的年审详细信息
    myClubBackboneExamData: admin + '/myClubBackboneExamData',//获取特定社团的骨干评优信息
    myClubTeacherExamData: admin + '/myClubTeacherExamData',//获取特定社团的骨干评优信息

    // post
    adminInfoUpdate: admin + '/adminInfoUpdate',//更新管理员信息
    passClubApproval: admin + '/passClubApproval',  // 通过社团建立申请
    unPassClubApproval: admin+ '/unPassClubApproval',   // 驳回社团建立申请
    passActivityApproval: admin + '/passActivityApproval',  // 通过社团建立申请
    unPassActivityApproval: admin+ '/unPassActivityApproval',   // 驳回社团建立申请
    passBackboneAwardsReview: admin + '/passBackboneAwardsReview',  // 通过骨干评优申请
    unPassBackboneAwardsReview: admin + '/unPassBackboneAwardsReview',  // 驳回骨干评优申请
    passClubAnnualReview: admin + '/passClubAnnualReview',  // 通过社团年审
    unPassClubAnnualReview: admin + '/unPassClubAnnualReview', //驳回社团年审
    passClubAwardReview: admin + '/passClubAwardReview',  // 通过社团评优
    unPassClubAwardReview: admin + '/unPassClubAwardReview', //驳回社团评优


    /*————————————————社团————————————————*/
    // get
    clubsInfo: club + '/clubsInfo', // 获取所有社团信息
    clubActAndNtc: club + '/clubActAndNtc', // 获取社团活动通知
    clubIntroduction: club + '/clubIntroduction', // 获取社团简介
    clubMembers: club + '/clubMembers', // 获取社团成员
    clubApplyList: club + '/clubApplyList', // 获取社团申请列表
    // clubEvaluateInfo: club + '/clubEvaluateInfo',// 获取社团评优所需信息
    topTenClubs: club + '/topTenClubs', // 获取十佳社团
    clubNoticeList: club + '/clubNoticeList', // 获取社团通知列表
    associationAwards: club + '/associationAwards', // 获取社团获奖信息
    meetings: club + '/meetings', // 获取社团会议列表
    clubApplicationInfo: club + '/clubApplicationInfo', // 获取建立社团申请详细信息
    clubAnnual: club + '/getClubAnnual', // 获取详细年审信息
    clubAward: club + '/getClubAward',  // 获取详细社团评优信息
    clubInfo: club + '/clubInfo',   // 获取详细社团信息
    thisYearClubAnnual: club + '/thisYearClubAnnual', // 获取今年社团年审信息

    // post
    joinClub: club + '/joinClub', // 加入社团
    agreeClubApply: club + '/agreeClubApply', // 同意社团申请
    rejectClubApply: club + '/rejectClubApply', // 拒绝社团申请
    newClubApply: club + '/newClubApply', // 提交新社团申请
    deleteClubMember: club + '/deleteClubMember', // 删除社团干部，即将社团干部变成普通成员
    updateClubMember: club + '/updateClubMember', // 更新社团干部信息
    addClubMember: club + '/addClubMember', // 添加社团干部
    updateClubInfo: club + '/updateClubInfo', // 更新社团信息
    clubAnnualAuditForm: club + '/clubAnnualAuditForm', // 提交社团年审表
    clubEvaluationForm: club + '/clubEvaluationForm', // 提交社团评优表
    newNotice: club + '/newNotice', // 提交新通知
    newMeeting: club + '/newMeeting', // 提交新会议
    updateClubDescription: club + '/updateClubDescription', // 更新社团简介


    /*——————————————活动——————————————*/
    // get
    activityInfo: activity + '/activityInfo', // 获取活动信息
    activitiesInfo: activity + '/activitiesInfo', // 获取所有活动信息
    latestActivities: activity + '/latestActivities', // 获取最新活动
    clubActivityList: activity + '/clubActivityList', // 获取社团活动列表

    // post
    newActivity: activity + '/newActivity', // 提交新活动
    personalPerformance: activity + '/personalPerformance', // 提交个人活动表现
    activityPerformance: activity + '/activityPerformance', // 提交活动表现
    joinActivity: activity + '/joinActivity', // 参加活动


    /*——————————————文件——————————————*/
    uploadImage: file + '/uploadImage', // 上传图片
    uploadFile: file + '/uploadFile', // 上传文件



}

export default base;