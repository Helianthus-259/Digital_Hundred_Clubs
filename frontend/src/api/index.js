import { APIEventEnum } from '@/Enum'
import axios from '../utils/request'
import path from './path'
import eventEmitter from '@/utils/eventEmitter'

const api = {
    'postLogin': (params) => axios.post(path.login, { // 登录
        email: params.email,
        password: params.password,
    }),
    'postRegister': (params) => axios.post(path.register, { // 注册
        email: params.email,
        verifyCode: params.verifyCode,
        password: params.password,
    }),
    'getVerifyCode': (params) => axios.get(path.verifyCode, { // 获取验证码
        params: {
            email: params.email,
        },
    }),
    'getClubsInfo': (params) => axios.get(path.clubsInfo, { // 获取社团信息

    }),
    'getclubLatestAct': (params) => axios.get(path.clubLatestAct, { // 获取社团最新活动

    }),
    'getUserInfo': (params) => axios.get(path.userInfo, { // 获取用户信息
        params: {
            userID: params.uid,
        }
    }),
    'postUserInfo': (params) => axios.post(path.userInfoUpdate, { // 更新用户信息
        params: {
            userID: params.uid,
            phone: params.phone,
            hobby: params.hobby,
            specialty: params.specialty
        }
    }),
    'postNewClubApply': (params) => axios.post(path.newClubApply, { // 申请创建社团
        params: {
            uid: params.uid,
            name: params.name,
            introduction: params.introduction,
            clubType: params.clubType,
            leader: params.leader,
            leaderPhone: params.leaderPhone,
            campus: params.campus,
            faculty: params.faculty,
            teacher: params.teacher,
            businessTeacher: params.businessTeacher,
            material: params.material,
        }
    }),
    'getClubActAndNtc': (params) => axios.get(path.clubActAndNtc, { // 获取社团活动通知
        params: {
            clubID: params.clubID,
        }
    }),
    'getClubIntroduction': (params) => axios.get(path.clubIntroduction, { // 获取社团简介
        params: {
            clubID: params.clubID,
        }
    })
}

eventEmitter.on(APIEventEnum.request, async (method, params) => {
    await api[method](params)
})

export default api