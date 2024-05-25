export const APIEventEnum = {
    // 请求事件
    request: 'request',

    // 请求成功事件
    loginSuccess: 'loginSuccess',
    getClubsInfoSuccess: 'getClubsInfoSuccess',
    getUserInfoSuccess: 'getUserInfoSuccess',
    getClubActAndNtcSuccess: 'getClubActAndNtcSuccess',
    getClubIntroductionSuccess: 'getClubIntroductionSuccess',
    getClubMembersSuccess: 'getClubMembersSuccess',
    getAdminInfoSuccess: 'getAdminInfoSuccess',
    getActivityInfoSuccess: 'getActivityInfoSuccess',
    getActivitiesInfoSuccess: 'getActivitiesInfoSuccess',
    getClubActivityListSuccess: 'getClubActivityListSuccess',
    getClubApplyListSuccess: 'getClubApplyListSuccess',
    postAgreeClubApplySuccess: 'postAgreeClubApplySuccess',
    postRejectClubApplySuccess: 'postRejectClubApplySuccess',
    postNewActivitySuccess: 'postNewActivitySuccess',
    postBackBoneEvaluateSuccess: 'postBackBoneEvaluateSuccess',

    // 请求失败事件
    incorrectInput: 'incorrectInput',
    registerError: 'registerError',
}

export const APIEnum = {
    postLogin: 'postLogin',
    postRegister: 'postRegister',
    getClubsInfo: 'getClubsInfo',
    getVerifyCode: 'getVerifyCode',
    getUserInfo: 'getUserInfo',
    postUserInfo: 'postUserInfo',
    postNewClubApply: 'postNewClubApply',
    getClubActAndNtc: 'getClubActAndNtc',
    getClubIntroduction: 'getClubIntroduction',
    getClubMembers: 'getClubMembers',
    postAdminLogin: 'postAdminLogin',
    getAdminInfo: 'getAdminInfo',
    postAdminInfo: 'postAdminInfo',
    getActivityInfo: 'getActivityInfo',
    getActivitiesInfo: 'getActivitiesInfo',
    getClubActivityList: 'getClubActivityList',
    getClubApplyList: 'getClubApplyList',
    postAgreeClubApply: 'postAgreeClubApply',
    postRejectClubApply: 'postRejectClubApply',
    postNewActivity: 'postNewActivity',
    postBackBoneEvaluate: 'postBackBoneEvaluate',
}


// 路由
export const RouterEventEnum = {
    push: 'push',
    go: 'go',
}

// 存储
export const StoreEventEnum = {
    set: 'set',
}

export const StoreEnum = {
    setInit: 'setInit',
    setClubsData: 'setClubsData',
    setUserInfo: 'setUserInfo',
    setRouteTabs: 'setRouteTabs',
    setParentRoute: 'setParentRoute',
    setclubsActAndNtc: 'setclubsActAndNtc',
    setClubId: 'setClubId',
    setAdminInfo: 'setAdminInfo',
    reset: 'reset',
}

// 展示的社团类型
export const TypeEventEnum = {
    addType: 'addType',
    removeType: 'removeType',
}