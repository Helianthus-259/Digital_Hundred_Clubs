const base = {
    login: '/api/login', // 登录
    register: '/api/register', // 注册
    verifyCode: '/api/verifyCode', // 验证码
    clubsInfo: '/api/clubsInfo', // 获取所有社团信息
    clubLatestAct: '/api/clubLatestAct', // 获取最新活动
    userInfo: '/api/userInfo', // 获取用户信息
    userInfoUpdate: '/api/userInfoUpdate', // 更新用户信息
    newClubApply: '/api/newClubApply', // 提交新社团申请
    clubActAndNtc: '/api/clubActAndNtc', // 获取社团活动通知
    clubIntroduction: '/api/clubIntroduction', // 获取社团简介
    clubMembers: '/api/clubMembers', // 获取社团成员
    postAdminLogin: '/api/postAdminLogin', //管理员登录
    adminInfo: 'api/adminInfo',//获取管理员信息
    adminInfoUpdate: 'api/adminInfoUpdate',//获取管理员信息
    activityInfo: '/api/activityInfo', // 获取活动信息
    activitiesInfo: '/api/activitiesInfo', // 获取所有活动信息
    clubActivityList: '/api/clubActivityList', // 获取社团活动列表
}

export default base;