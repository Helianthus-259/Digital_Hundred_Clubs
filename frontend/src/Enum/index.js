export const APIEventEnum = {
    // 请求事件
    request: 'request',

    // 请求成功事件
    getClubsInfoSuccess: 'getClubsInfoSuccess',
    getUserInfoSuccess: 'getUserInfoSuccess',
    getClubActAndNtcSuccess: 'getClubActAndNtcSuccess',
    getClubIntroductionSuccess: 'getClubIntroductionSuccess',

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
    setClubID: 'setClubID',
}

// 展示的社团类型
export const TypeEventEnum = {
    addType: 'addType',
    removeType: 'removeType',
}