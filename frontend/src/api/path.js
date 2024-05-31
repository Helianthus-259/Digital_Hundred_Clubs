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


    /*——————————————学生——————————————*/
    // get
    userInfo: student + '/userInfo', // 获取用户信息
    verifyCode: student + '/verifyCode', // 获取验证码

    // post

    userInfoUpdate: student + '/userInfoUpdate', // 更新用户信息
    backBoneEvaluate: student + '/backBoneEvaluate', // 提交骨干评优申请


    /*———————————————管理员——————————————*/
    // get
    adminInfo: admin + '/adminInfo',//获取管理员信息
    backBoneEvaluations: admin + '/backBoneEvaluations',  // 获取所有的骨干评优信息
    clubAnnuals: admin + '/clubAnnuals',    // 获取所有社团年审
    clubEvaluations: admin + '/clubEvaluations', // 获取所有社团评优

    // post
    adminInfoUpdate: admin + '/adminInfoUpdate',//更新管理员信息


    /*————————————————社团————————————————*/
    // get
    clubsInfo: club + '/clubsInfo', // 获取所有社团信息
    clubActAndNtc: club + '/clubActAndNtc', // 获取社团活动通知
    clubIntroduction: club + '/clubIntroduction', // 获取社团简介
    clubMembers: club + '/clubMembers', // 获取社团成员
    clubActivityList: club + '/clubActivityList', // 获取社团活动列表
    clubApplyList: club + '/clubApplyList', // 获取社团申请列表
    clubEvaluateInfo: club + '/clubEvaluateInfo',// 获取社团评优所需信息
    topTenClubs: club + '/topTenClubs', // 获取十佳社团

    // post
    agreeClubApply: club + '/agreeClubApply', // 同意社团申请
    rejectClubApply: club + '/rejectClubApply', // 拒绝社团申请
    newClubApply: club + '/newClubApply', // 提交新社团申请
    deleteClubMember: club + '/deleteClubMember', // 删除社团干部，即将社团干部变成普通成员
    updateClubMember: club + '/updateClubMember', // 更新社团干部信息
    addClubMember: club + '/addClubMember', // 添加社团干部
    updateClubInfo: club + '/updateClubInfo', // 更新社团信息


    /*——————————————活动——————————————*/
    // get
    activityInfo: activity + '/activityInfo', // 获取活动信息
    activitiesInfo: activity + '/activitiesInfo', // 获取所有活动信息
    latestActivities: activity + '/latestActivities', // 获取最新活动

    // post
    newActivity: activity + '/newActivity', // 提交新活动
    personalPerformance: activity + '/personalPerformance', // 提交个人活动表现
    activityPerformance: activity + '/activityPerformance', // 提交活动表现


    /*——————————————文件——————————————*/
    uploadImage: file + '/uploadImage', // 上传图片
    uploadFile: file + '/uploadFile', // 上传文件



}

export default base;