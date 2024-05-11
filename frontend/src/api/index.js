import { APIEventEnum } from '@/Enum'
import axios from '../utils/request'
import path from './path'
import eventEmitter from '@/utils/eventEmitter'

const api = {
    'postLogin': (params) => axios.post(path.login, { // 登录
        email: params.email,
        pwd: params.pwd,
    }),
    'postRegister': (params) => axios.post(path.register, { // 注册
        email: params.email,
        verifyCode: params.verifyCode,
        pwd: params.pwd,
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
            studentId: params.studentId,
        }
    }),
    'postUserInfo': (params) => axios.post(path.userInfoUpdate, { // 更新用户信息
        params: {
            studentId: params.studentId,
            contact: params.contact,
            hobby: params.hobby, // 后端还没有
            specialty: params.specialty // 后端还没有
        }
    }),
    'postNewClubApply': (params) => axios.post(path.newClubApply, { // 申请创建社团
        params: {
            contactPersonId: params.studentId,
            clubName: params.clubName,
            clubDescription: params.clubDescription,
            clubCategory: params.clubCategory,
            mainCampus: params.mainCampus,
            administrativeGuideTeacherName: params.administrativeGuideTeacherName,
            businessGuideTeacherName: params.businessGuideTeacherName,
            advisorResumeAttachmentUrl: params.material,
        }
    }),
    'getClubActAndNtc': (params) => axios.get(path.clubActAndNtc, { // 获取社团活动通知
        params: {
            clubId: params.clubId,
        }
    }),
    'getClubIntroduction': (params) => axios.get(path.clubIntroduction, { // 获取社团简介
        params: {
            clubId: params.clubId,
        }
    }),
    'getClubMembers': (params) => axios.get(path.clubMembers, { // 获取社团成员
        params: {
            clubId: params.clubId,
        }
    }),
    'postAdminLogin':(params) => axios.post(path.postAdminLogin, { //管理员登录
        adminId: params.adminId,
        password: params.password,        
    }),
}

eventEmitter.on(APIEventEnum.request, async (method, params) => {
    await api[method](params)
})

export default api