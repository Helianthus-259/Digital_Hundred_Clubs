const base = {
    // 学生
    // get
    userInfo: '/api/userInfo', // 获取用户信息
    verifyCode: '/api/verifyCode', // 验证码

    // post
    login: '/api/login', // 登录
    register: '/api/register', // 注册
    userInfoUpdate: '/api/userInfoUpdate', // 更新用户信息
    backBoneEvaluate: '/api/backBoneEvaluate', // 骨干评优


    // 管理员
    // get
    adminInfo: 'api/adminInfo',//获取管理员信息

    // post
    postAdminLogin: '/api/postAdminLogin', //管理员登录
    adminInfoUpdate: 'api/adminInfoUpdate',//更新管理员信息


    // 社团
    // get
    clubsInfo: '/api/clubsInfo', // 获取所有社团信息
    clubLatestAct: '/api/clubLatestAct', // 获取最新活动
    clubActAndNtc: '/api/clubActAndNtc', // 获取社团活动通知
    clubIntroduction: '/api/clubIntroduction', // 获取社团简介
    clubMembers: '/api/clubMembers', // 获取社团成员
    clubActivityList: '/api/clubActivityList', // 获取社团活动列表
    clubApplyList: '/api/clubApplyList', // 获取社团申请列表
    clubEvaluateInfo: '/api/clubEvaluateInfo',// 获取社团评优所需信息

    // post
    agreeClubApply: '/api/agreeClubApply', // 同意社团申请
    rejectClubApply: '/api/rejectClubApply', // 拒绝社团申请
    newClubApply: '/api/newClubApply', // 提交新社团申请


    // 活动
    // get
    activityInfo: '/api/activityInfo', // 获取活动信息
    activitiesInfo: '/api/activitiesInfo', // 获取所有活动信息

    // post
    newActivity: '/api/newActivity', // 提交新活动


    // 文件
    uploadImage: '/api/uploadImage', // 上传图片




}

export default base;