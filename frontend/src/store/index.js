import { StoreEnum, StoreEventEnum } from '@/Enum'
import eventEmitter from '@/utils/eventEmitter'
import { createStore } from 'vuex'

const store = createStore({
    state: {
        token: '',
        studentId: -1,
        adminId: -1,
        clubsData: [],
        userInfo: {},

        // 当前访问的ClubId
        clubId: -1,

        // 路由存储，防止刷新后tabs的值发生改变
        routeTabs: {
            'firstPageTabs': 'home',
            'homeTabs': 'clubs',
            'adminClubTabs': 'adminClub',
            'personalTabs': '',
            'clubTabs': '',
            'adminTabs': 'admin',
            'adminFirstPageTabs': 'admin',
            'examineTabs': 'examine',
            'adminManageTabs': 'review',
            'adminPersonalTabs': 'adminPersonalInfo',
            'clubAnnualReview': 'clubAnnualReview',
            'clubAwardsReview': 'clubAwardsReview',
            'backboneAwardsReview': 'backboneAwardsReview',
        },

        // 有子路由的界面的初始路由
        parentRoute: {
            'firstPage': '/',
            'home': '/home/',
            'admin': '/adminFirstPage/',
            'adminPersonal': '/adminFirstPage/adminPersonal/',
            'personal': '/personal/-1/',
            'club': '/club/-1/',
            'activity': '/activity/',
            'clubManage': '/clubManage/',
            'examine': '/adminFirstPage/examine/',
            'review': '/adminFirstPage/review/',
        },

        // 社团比较新的活动和通知
        clubsActAndNtc: {}
    },
    mutations: {
        setInit(state, { token, studentId }) { // 保存token和studentId
            state.token = token
            sessionStorage.setItem('token', token)
            state.studentId = studentId
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setParentRoute, { owner: 'personal', value: studentId })
        },
        setClubsData(state, clubsData) { // 保存获取的社团信息
            state.clubsData = clubsData
        },
        setUserInfo(state, userInfo) { // 保存用户信息
            state.userInfo = userInfo
        },
        setRouteTabs(state, { owner, value }) { // 保证页面刷新后还能保留在之前的页面
            state.routeTabs[owner] = value
        },
        setClubId(state, clubId) { // 保存当前访问的ClubId
            state.clubId = clubId
        },
        // 处理会变化的有子路由的界面的初始路由
        setParentRoute(state, { owner, value }) {
            state.parentRoute[owner] = `/${owner}/${value}/`
            if (owner === 'club') {
                // 重置clubTabs
                state.routeTabs.clubTabs = ''
            }
        },

        setclubsActAndNtc(state, data) {
            state.clubsActAndNtc[data.clubId] = data
        },
        reset(state) { // 重置状态
            state.token = ''
            state.studentId = -1
            state.adminId = -1
            state.clubsData = []
            state.userInfo = {}
            state.clubId = -1
            state.routeTabs = {
                'firstPageTabs': 'home',
                'homeTabs': 'clubs',
                'adminClubTabs': 'adminClub',
                'personalTabs': '',
                'clubTabs': '',
                'adminTabs': 'admin',
                'adminFirstPageTabs': 'admin',
                'examineTabs': 'examine',
                'adminManageTabs': 'review',
                'adminPersonalTabs': 'adminPersonalInfo',
                'clubAnnualReview': 'clubAnnualReview',
                'clubAwardsReview': 'clubAwardsReview',
                'backboneAwardsReview': 'backboneAwardsReview',
            }
            state.parentRoute =  {
                'firstPage': '/',
                'home': '/home/',
                'admin': '/adminFirstPage/',
                'adminPersonal': '/adminFirstPage/adminPersonal/',
                'personal': '/personal/-1/',
                'club': '/club/-1/',
                'activity': '/activity/',
                'clubManage': '/clubManage/',
                'examine': '/adminFirstPage/examine/',
                'review': '/adminFirstPage/review/',
            }
            state.clubsActAndNtc = {}
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setInit, { token: '', studentId: -1 })
        }
    },
    getters: {
        // 定义获取状态的函数

    },
    modules: {
        // 定义模块

    },
})

eventEmitter.on(StoreEventEnum.set, 'set', (method, param) => {
    store.commit(method, param)
})

export default store