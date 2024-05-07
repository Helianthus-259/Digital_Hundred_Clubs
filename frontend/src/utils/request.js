import axios from "axios";
import eventEmitter from "./eventEmitter";
import { APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum } from "@/Enum";

const instance = axios.create({
    timeout: 5000
})

instance.interceptors.request.use(
    config => {
        // 设置请求头
        const token = sessionStorage.getItem('token')
        if (token) {
            config.headers.Authorization = token
        }
        return config // 返回配置
    },
    error => {
        return Promise.reject(error) // 返回错误信息
    }
)

const errorHandler = error => {
    if (error.response.status === 1000) {
        eventEmitter.emit(APIEventEnum.incorrectInput, error.response.data.msg)
    } else if (error.response.status === 1001) {
        eventEmitter.emit(APIEventEnum.registerError, error.response.data.msg)
    }
}

function successHandler(response) {
    if (response.status === 200) {
        if (response.data.code === 1) { // 登录成功
            const { token, uid } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setInit, { token, uid })
            eventEmitter.emit(RouterEventEnum.go, -1)
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'firstPageTabs', value: 'home' })
        } else if (response.data.code === 2) { // 获取社团信息成功
            const { data } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubsData, data)
            eventEmitter.emit(APIEventEnum.getClubsInfoSuccess, data)
        } else if (response.data.code === 3) { // 获取用户信息成功
            const { data } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, data)
            eventEmitter.emit(APIEventEnum.getUserInfoSuccess, data)
        } else if (response.data.code === 4) { // 更新用户信息成功
            console.log('更新用户信息成功');
        } else if (response.data.code === 5) { // 新建社团申请成功
            console.log('新建社团申请成功');
        } else if (response.data.code === 6) { // 获取社团活动通知成功
            const { clubID, activities, notices } = response.data
            eventEmitter.emit(StoreEventEnum.set, StoreEnum.setclubsActAndNtc, { clubID, activities, notices })
            eventEmitter.emit(APIEventEnum.getClubActAndNtcSuccess, { activities, notices })
        } else if (response.data.code === 7) { // 获取社团简介成功
            const { introduction } = response.data
            eventEmitter.emit(APIEventEnum.getClubIntroductionSuccess, introduction)
        }
    }
}

instance.interceptors.response.use(
    response => {
        successHandler(response)
        return response
    },
    error => {
        errorHandler(error)
    }
)
export default instance;