// 枚举类
const list={
    status:[null, 0, 1],// 待审核， 失败， 通过
    campus:["北校区", "南校区", "东校区", "珠海校区", "深圳校区"],
    category: ["学术类", "体育类", "艺术类", "公益类", "科技类", "其他类"],
    meeting:[0, 1],// 骨干例会/全员大会
    politicalStatus:['群众','共青团员','预备党员','党员'],
    position:[0, 1, 2, 3],// 社长、副社长、干部、普通成员
}
//url
const url_data = {
    /*——————————————认证中心——————————————*/
    // url:/api/auth/login
    login:{// 登录
        params: {// post参数
            email: '',  // 邮箱
            pwd: '',    // 密码
        },
        // 如果账号密码正确
        success:{
            code: 1, // 1代表注册/登录成功
            token:'',   // 安全令牌
            studentId:1, // 学生个人标识id
        },
        // 如果账号或密码错误
        failed:{
            msg:'用户名或密码错误'
        },
    },
    // url:/api/auth/register
    register:{// 注册
        params:{// post参数
            email:'',   // 用户邮箱
            verifyCode:'',  // 验证码
            pwd:'', // 密码
        },
        // 如果注册成功
        success:{
            code: 1,    // 1代表注册/登录成功
            token: '',  // 令牌
            studentId: '',  // 学生个人标识id
        },
        // 如果注册失败
        failed:{
            msg:'注册失败提示信息'  //邮箱重复或验证码不正确
        }
    },
    // url:/api/auth/adminLogin
    postAdminLogin:{// 管理员登录
        params: {// post参数
            adminId: '',    //管理员账号
            password: '',   //管理员密码
        },
        // 登陆成功
        success:{
            code: 9,    // 代表管理员登录成功
            token:'',   // 令牌
            uid:'',     // 管理员uid
        },
        // 登录失败
        failed:{
            msg:'',   // 登陆失败原因
        },
    },
    // url:/api/auth/verifyCode
    verifyCode:{// 获取验证码
        params:{// get参数
            email: '',  // 用户邮箱
        },
        data:{
            verifyCode:''   //验证码
        },
    },
    // url:/api/auth/getImageVerifyCode
    getImageVerifyCode:{// 获取图形验证码
        data:{
            code: 39,   // 39代表获取图片验证码成功
            imageUrl: 'src/assets/verify.jpg',   // 连接后端处理
        }
    },

    /*——————————————学生——————————————*/
    // get
    // url:/api/student/userInfo
    userInfo:{// 获取用户信息
        code: 3,// 前端用来识别业务的code，此处代表个人信息
        data:{
            stName: '张三',   // 用户姓名
            gender: '男',    //  性别
            dateOfBirth: '2000-01-01',  // 出生年份
            grade: '2019级', //  年级
            studentNumber: '2019001001',    //学号
            college: '信息科学学院',  //学院
            politicalStatus: '共青团员',    //政治面貌
            email: 'admin@mail2.sysu.edu.cn',   //邮箱
            contact: '13800138000', //联系方式
            hobby: '篮球',    //爱好
            specialty: '计算机科学与技术',  //专业
            clubs: [    //新建社团记录数据
                {
                    clubId: 1,  //社团id
                    clubName: '社团1',    //社团名
                    position: '社长', //职位
                    clubStatus: 0,  // 社团状态 1 通过 : 0 不通过
                    collegeReviewStatus: 0,     //学院审核状态 null 待审核 : 0 不通过 : 1 通过
                    universityStudentUnionReviewStatus: null,   //校团委审核状态 null 待审核 : 0 不通过 : 1 通过
                    collegeReviewOpinion: '社团理念不行', //学校审核意见
                    universityStudentUnionReviewOpinion: '',    //校团委审核意见
                },
            ],
            achievements: [ //个人获奖情况
                {
                    index: 1,   //记录id
                    activityName: '活动xxx', // 获得荣誉的活动名称
                    personalEffect: 'xxx活动xxx名', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
            ]
        }
    },

    // post
    // url:/api/student/userInfoUpdate
    userInfoUpdate:{// 更新用户信息
        params:{// post参数
            studentId:'',
            contact: '',    //联系方式
            hobby: '',  // 兴趣爱好
            specialty: ''   //专业
        },
        // 更新成功
        success:{
            code: 4,
        }
    },
    // url:/api/student/backBoneEvaluate
    backBoneEvaluate:{// 提交骨干评优申请(后端需要加审核状态?)
        params:{// post参数
            clubId: '', // 社团标识id
            studentId: '',  // 学生标识id
            tenurePeriod: '',   // 任职时长
            achievements: '',   // 学生个人成绩
            trainingParticipation: '',  // 参与学校和指导单位组织培训情况
            awards: '', // 骨干身份获得的奖励
            clubWorkStatus: '', // 学生社团工作情况
        },
        success:{
            code: 19,// 标识骨干评优业务
        }
    },

    /*———————————————管理员(管理员模块前端未完成)——————————————*/
    // get
    // url:/api/admin/adminInfo(学院管理员和校团委管理员的权能前端未确定)
    adminInfo:{// 获取管理员信息
        params:{// get参数
            adminId:'', // 管理员标识id
        },
        code: 10,// 代表管理员信息
        data:{
            adminId: '',    // 管理员标识id
            account: '',    // 管理员账号
            sort: '', //0代表学院管理员,1代表校级管理员
            password: '',   // 管理员密码
            contact: '',    //联系方式
            affiliatedUnit: '', // 所属单位名称
        }
    },
    // url:/api/admin/backBoneEvaluations
    backBoneEvaluations:{// 获取所有的骨干评优信息
        code: 26, // 代表获取骨干业务
        data:[// 提取所有的骨干评优信息
            {
                recordId:'',    // 骨干评优记录id
                stName: '', // 学生姓名
                studentNumber: '',  // 学号
                contact: '',    // 联系方式
                college: '',    // 学院
                politicalStatus: '',    // 政治面貌
                clubName: '',   // 社团名
                position: '',   // 职位
                tenurePeriod: '',   // 任职时间
                achievements: {
                    gpa: '',    // 绩点
                    rank: '',   // 排名
                    rankRatio: '',  // 排名百分率
                },
                trainingParticipation: [    // 个人培训经历
                    { time: '2022', location: '党校', organization: '学院' },
                ],
                associationAwards: [    // 社团获奖记录
                    { name: 'xx社团奖项', time: '2022', organization: '学院' },
                ],
                awards: [   // 个人获奖记录
                    { name: 'xx个人奖项', time: '2022', organization: '学院' },
                ],
                clubWorkStatus: '工作描述',
            },
        ]
    },
    // url:/api/admin/clubAnnuals
    clubAnnuals:{// 获取所有社团年审
        code: 27,   // 27代表获取社团年审
        data:{  // 获取所有社团年审记录
            declarationId:'',   // 申请id
            declarationYear: '',    // 申请年份
            clubName: '',   // 社团名
        }
    },
    // url:/api/admin/clubEvaluations
    clubEvaluations:{// 获取所有社团评优
        code: 28,   // 28代表社团评优成功
        data:{  // 获取所有社团评优记录
            recordId: '',   // 记录id
            declarationYear: '',    // 申请年份
            clubId: '', // 社团id
            clubName: '',   // 社团名
            handoverMethod: '',//0全员大会,1骨干例会
            handoverParticipantsCount: '',  // 参加人数
            guideTeacher: '',   // 指导教师是否参与指导
            meetings: [ // 全员/骨干大会情况
                {
                    activityId: '', // 会议id
                    clubId: '', // 社团id
                    time: '',   // 会议时间
                    location: '',   // 会议地点
                    staffMeetingOrbackBoneMeeting: '',//0全员大会,1骨干例会
                    guideTeacher: '',   // 是否有指导老师
                }
            ],
            associationAwards: [    // 社团获奖情况
                {
                    name: '',   // 奖项名称
                    time: '',   // 获奖时间
                    organization: ''    // 颁奖单位
                }
            ],
            publicityManagementEffectiveness: {// 宣传情况
                submissionsCount: '',   // 向学校投稿次数
                PublicityAboveSchoolLevel: [    // 获校级以上宣传情况
                    {
                        platform: "平台",
                        content: "内容",
                    }
                ],
            },
            hostedSchoolLevelActivities: {  // 承办校级以上活动记录
                schoolLv: [     // 校级
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ],
                municipal: [    // 市级
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ],
                provincial: [   // 省级
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ]
            },
            activities: [   // 社团活动记录
                {
                    activityName: "活动名称",
                    activityTime: "活动时间",
                    activityEffect: "活动成效",
                }
            ],
            clubWorkIntroduction: "工作简介"
        }
    },



    // post
    // url:/api/admin/adminInfoUpdate
    adminInfoUpdate:{// 更新管理员信息
        params: {//post参数
            adminId: '',    // 管理员标识id
            clubIntroduction: '',   // 管理社团信息？
        },
        // 更新成功
        success:{
            code: 11,
        }
    },

    /*——————————————社团——————————————*/
    // get
    // url:/api/club/clubsInfo
    clubsInfo:{// 获取所有社团信息
        code: 2,
        data:[
            {// 所有的社团信息
                imageUrl: '',   // 社团展示图片
                clubName: '',   // 社团名称
                clubDescription: '',    // 社团介绍
                campus: '', // 社团所在校区
                totalMembers: '',   // 总人数
                clubId: '', // 社团标识id
                clubCategory: '',   // 社团类别
                createTime: '', // 成立时间(作为审核的申请时间)
                status: '', // 审核状态 null 待审批; 1 审批通过 ;0 审批失败
            },
        ]
    },
    // url:/api/club/clubActAndNtc
    clubActAndNtc:{// 获取社团活动通知
        params:{// get参数
            clubId:''   // 请求的社团id
        },
        data:{
            code: 6,// 6代表获取社团活动（单个）通知
            clubId:'',//社团标识id
            activities:[
                {// 社团所有活动
                    activityId: '', // 活动标识id
                    activityName: '',   // 活动名称
                    activityIntroduction: '',   // 活动介绍
                    activityStartTime: '',  // 活动开始时间
                    activityEndTime: '',    // 活动结束时间
                    activityLocation: '',   // 活动地点
                    imageUrl: '',   // 活动宣传图片
                },
            ],
            notices:[
                {// 社团所有通知
                    announcementId: '', // 通知标识id
                    title: '',  // 通知标题
                    content: '',    // 通知内容
                    imageUrl: '',   // 通知图片
                    publishTime: '',    // 通知发布时间
                },
            ]
        }
    },
    // url:/api/club/clubIntroduction
    clubIntroduction:{// 获取社团简介
        params:{// get参数
            clubId:''   // 社团标识id
        },
        data:{
            code: 7,    // 7代表获取社团简介
            clubDescription: [
                {
                    title: '社团成立',
                    content: '',
                },
                {
                    title: '社团介绍',
                    content: '',
                },
                {
                    title: '社团成就',
                    content: ''
                }
            ]
        }
    },
    // url:/api/club/clubMembers
    clubMembers:{// 获取社团成员
        params:{// get参数
            clubId: ''  // 社团标识id
        },
        data:{
            code: 8,    // 8代表获取社团成员
            members:{   // 社团所有成员信息
                president:{ // 社长
                    stName: '', // 姓名
                    position: '',   // 职位
                    contact: '',    // 联系方式
                    imageUrl: '',   // 头像
                },
                executives: [ // 骨干
                    {
                        stName: '', // 姓名
                        position: '',   // 职位
                        imageUrl: '',   // 头像
                    },
                ],
                others: {
                    totalMembers: '',   // 社团总人数
                }
            }
        }
    },
    // url:/api/club/clubActivityList
    clubActivityList:{// 获取社团活动列表
        params:{// get参数
            clubId: '', // 社团标识id
            // 通过后端指定搜索范围实现分页
            pageNumber: '', //  第n次请求的数据
            pageSize: '',   //  请求的数据量
        },
        data:{
            code: 14,   // 14代表获取社团活动列表
            activityList:[// 该社团所有的活动
                {
                    activityId: '', // 活动标识id
                    activityName: '',   // 活动名称
                    imageUrl: '',   // 活动宣传图
                    status: '', // 活动审核状态 null 待审核; 0 审核驳回; 1 审核成功
                },
            ]
        }
    },
    // url:/api/club/clubApplyList
    clubApplyList:{// 获取社团申请列表
        params:{// get参数
            clubId:''   // 社团标识id
        },
        data:{
            code: 15,   // 15代表获取社团申请列表(加入社团)
            applyList:[
                {
                    studentId: '',  // 学生标识id
                    studentName: '',    // 学生姓名
                    college: '',    // 学生所属学院
                    politicalStatus: '',    //  学生政治面貌
                    status: '', // 入社审核状态
                    email: '',  // 学生邮箱
                    createTime: '', // 申请日期
                    reason: '', // 申请原因
                },
            ]
        }
    },
    // url:/api/club/clubEvaluateInfo
    clubEvaluateInfo:{// 获取社团评优/年审所需信息
        params:{// get参数
            clubId:''   // 社团标识id
        },
        data:{
            code: 20,   // 20代表获取社团评优所需信息
            clubName: '',   // 社团名
            totalMembers: '',   // 社团总人数
            isFinancialInformationPublic: '',   // 是否财务公开
            administrativeGuideTeacherName: '', // 行政指导老师姓名
            businessGuideTeacherName: '',   // 业务指导老师姓名
            backboneNumber: '', // 骨干人数
            establishmentDate: '',  // 成立时间
            responsibleDepartment: '',  // 负责单位
            mainCompus: '', // 社团所在校区
            clubDescription: '',    // 社团描述
            clubCategory: '',   // 社团类别
            contactPerson: '',  // 联系人
            politicalStatus: '',    // 申请人政治面貌
            contactPhone: '',   // 联系人电话
            clubStatus: '', // 社团状态
            file: '',   // 所需文件
            communistRelatedBackBoneNumber: '', // 学生骨干是中共党员、入党积极分子或提交入党申请书人数
            publicityManagementInfo: {// 宣传详情
                WeChatPublicAccount: { name: '', submitCount: '', Auditors: { name1: '', name2: '', name3: '' } },
                WebSite: { has: '', name: '' },
                Blog: { has: '', name: '' },
                BiliBili: { has: '', name: '' },
                TikTok: { has: '', name: '' },
                WeChatVideoAccount: { has: '', name: '' },
                KuaiShou: { has: '', name: '' },
                Publication: { has: '', name: '' },
                Other: { has: '', name: '' },
            },
        }
    },
    // url:/api/club/topTenClubs
    topTenClubs:{// 获取十佳社团
        data:{
            code: 32,   // 代表获取十佳社团成功
            clubs:[// 一共10个社团
                {
                    clubId:'',  //社团id
                    clubName:'',    // 社团名
                    imageUrl:'',    // 社团宣传图
                },
            ]
        }
    },
    // url:/api/club/clubNoticeList
    clubNoticeList:{// 获取社团通知列表
        params:{// get参数
            clubId:'',  // 社团标识id
        },
        data:{
            code: 36,   // 36代表获取社团通知列表成功
            notices:[// 该社团所有的通知
                {
                    announcementId: '', // 通知id
                    title: '',  // 通知标题
                    content: '',    // 通知内容
                    imageUrl: '',   // 通知图片
                    publishTime: '',    // 通知发布时间
                    modifyTime: ''  // 通知修改时间
                },
            ]
        }
    },
    // url:/api/club/associationAwards
    associationAwards:{// 获取社团获奖信息
        params: {// get参数
            clubId: '', // 社团标识id
        },
        data:{
            code: 45,   // 45标识获取社团获奖信息成功
            associationAwards:[ // 所有的获奖信息
                {
                    awardName: '',  // 奖项名
                    awardTime: '',  // 获奖时间
                    issuingAuthority: '',   // 颁奖单位
                },
            ]
        }
    },
    // url:/api/club/meetings
    meetings:{
        params: {   // get参数
            clubId: '', // 社团标识id
        },
        data:{
            code: 46,   // 46代表获取社团会议信息成功
            meetings:[
                {
                    meetingTime: '',    // 会议时间
                    location: '',   // 地点
                    category: '',   // 会议类型
                },
            ]
        }
    },

    // post
    // url:/api/club/agreeClubApply
    agreeClubApply:{// 同意社团申请
        params: {// post参数
            clubId: '', // 社团标识id
            studentId: '',  // 学生标识id
        },
        data:{
            code: 16,   // 16代表同意社团申请(加入社团)
            studentId: '',  // 学生唯一标识id
        }
    },
    // url:/api/club/rejectClubApply
    rejectClubApply:{// 拒绝社团申请
        params: {// post参数
            clubId: '', // 社团标识id
            studentId: '',  // 学生标识id
            rejectReason: '',   // 拒绝原因
        },
        data:{
            code: 17, // 17代表拒绝社团申请(不同意加入社团)
            studentId: ''   // 学生标识id
        }
    },
    // url:/api/club/newClubApply
    newClubApply:{// 提交新社团申请
        params: {// post参数
            contactPersonId: '',    // 联系人id(学生id)
            clubName: '',   // 社团名
            clubDescription: '',    // 社团描述
            clubCategory: '',   // 社团类别
            mainCampus: '', // 社团所在校区
            administrativeGuideTeacherName: '', // 行政指导老师名
            businessGuideTeacherName: '',   // 业务指导老师名
            advisorResumeAttachmentUrl: '', // 入社材料
            // 缺少负责部门
        },
        data:{
            code: 5, // 5代表入社申请
        }
    },
    // url:/api/club/deleteClubMember
    deleteClubMember:{// 删除社团干部，即将社团干部变成普通成员
        params: {// post参数
            clubId: '', // 社团id
            studentNumber: '',  // 学号
        },
        data:{
            code: 22,   // 22代表删除社团干部成功
        }
    },
    // url:/api/club/updateClubMember
    updateClubMember:{// 更新社团干部信息
        params: {// post参数
            clubId: '', // 社团id
            oldStudent: '', // 旧干部id
            newStudent: '', // 新干部id
        },
        data:{
            code:23,    //23代表更新社团干部信息成功
        }
    },
    // url:/api/club/addClubMember
    addClubMember:{// 添加社团干部
        params: {// post参数
            clubId: '', // 社团id
            studentId: '',  // 学生id
            stName: '', // 学生姓名
            position: '',   // 职位
        },
        data:{
            code: 24,   // 24代表添加社团干部成功
        }
    },
    // url:/api/club/updateClubInfo
    updateClubInfo:{// 更新社团信息
        params: {
            clubId:'',  // 社团id(前端没给)
            clubName: '',   // 社团名称
            establishmentDate: '',  // 建立时间
            clubCategory: '',   // 社团类型
            responsibleDepartment: '',  // 负责部门
            administrativeGuideTeacherName: '', // 行政指导老师
            businessGuideTeacherName: '',   // 业务指导老师
            mainCompus: '', // 社团校区
            isFinancialInformationPublic: '',   // 财务是否公开
            imageUrl: '',   // 社团宣传图
        },
        data:{
            code: 25,// 25代表社团信息更新成功
        }
    },
    // url:/api/club/clubAnnualAuditForm
    clubAnnualAuditForm:{// 提交社团年审表
        params:{// post参数
            clubId:'',  // 社团id
            meetingActivityListAttachment: '',  // 活动及会议清单
            externalSponsorshipAttachment: '',  // 校外赞助附件
            isFinancialInformationPublic: '',   // 是否财务公开 0 否 ; 1 是
            clubConstitutionAttachment: '', // 社团章程附件
            responsibleDepartment: '',  // 负责部门
            publicityManagementInfo: {  // 宣传情况
                // json
                WeChatPublicAccount: { name: '', submitCount: '', Auditors: { name1: '', name2: '', name3: '' } },
                WebSite: { has: '0', name: '' },
                Blog: { has: '0', name: '' },
                BiliBili: { has: '0', name: '' },
                TikTok: { has: '0', name: '' },
                WeChatVideoAccount: { has: '0', name: '' },
                KuaiShou: { has: '0', name: '' },
                Publication: { has: '0', name: '' },
                Other: { has: '0', name: '' },
            },
        },
        data:{
            code: 34,   // 34代表提交年审表成功
        }
    },
    // url:/api/club/clubEvaluationForm
    clubEvaluationForm:{// 提交社团评优表
        params:{// post参数
            clubId: '', // 社团id
            handoverMethod: '', //  换届方式
            handoverParticipantsCount: '',  // 换届会参与人数
            advisorParticipation: '',   // 指导老师是否参与
            publicityManagementEffectiveness: { // 宣传管理成效
                submissionsCount: '',   // 向学校投稿次数
                PublicityAboveSchoolLevel:[// 所有的校级以上宣传情况
                    {// json
                        platform:'',    // 宣传平台
                        content: '',    // 宣传内容
                    },
                ]
            },
            hostedSchoolLevelActivities: {// 承办校级以上活动情况
                schoolLv: [// 校级
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ],
                municipal: [// 市级
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ],
                provincial: [// 省级
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ]
            },
            clubWorkIntroduction: '',   // 社团工作简介
            clubEducationCaseAttachment: '',    // 社团育人成效案例附件
            imageUrl: '',   // 社团代表性图片
        },
        data:{
            code: 35    // 35代表社团评优申请成功
        }
    },
    // url:/api/club/newNotice
    newNotice:{// 提交新通知
        params: {// post参数
            clubId: '', // 社团id
            title: '',  // 通知标题
            content: '',    // 通知内容
            imageUrl: '',   // 通知图片
        },
        data:{
            code: 37,   // 37代表提交新通知成功
        }
    },
    // url:/api/club/newMeeting
    newMeeting:{// 提交新会议
        params:{// post参数
            clubId: '', // 社团id
            meetingTime: '',    // 会议时间
            location: '',   // 会议地点
            category: '',   // 骨干例会/全员大会
            advisorName: '',    // 指导老师姓名
        },
        data:{
          code: 38  // 38代表发布新会议成功
        }
    },
    // url:/api/club/updateClubDescription
    updateClubDescription:{// 更新社团简介
        params:{// post参数
            clubId: '', // 社团id
            clubDescription: '',    // 新的社团简介
        },
        data:{
            code: 40,   // 40代表更新社团简介成功
        }
    },
    // url:/api/club/joinClub
    joinClub:{// 加入社团
        params: {   // post参数
            clubId: '', // 社团标识id
            studentId: '',  // 学生标识id
            reason: '', // 入社原因
        },
        data:{
            code: 47,   // 47代表加入社团申请
        }
    },


    /*——————————————活动——————————————*/
    // get
    // url:/api/activity/activityInfo
    activityInfo:{// 获取活动信息
        params: {// get参数
            activityId: '', // 活动标识id
        },
        data:{
            code: 12,   //
            activity: {
                activityId: '', // 活动标识id
                clubName: '',   // 社团名
                activityName: '',   // 活动名
                activityIntroduction: '',   // 活动介绍
                createTime: '', //申请时间
                activityStartTime: '',  // 活动开始时间
                activityEndTime: '',    // 活动结束时间
                activityLocation: '',   // 活动地点
                activityAttachment: '', // 活动附件
                status: ''  // 审核状态 1通过 :0未通过 :null待审核
            }
        }
    },
    // url:/api/activity/activitiesInfo
    activitiesInfo:{// 获取所有活动信息
        data:{
            code: 13,
            activities:[// 所有活动申请数据
                {
                    activityId: '', // 活动标识id
                    clubName: '',   // 社团名
                    activityName: '',   // 活动名
                    activityIntroduction: '',   // 活动介绍
                    createTime: '', //申请时间
                    activityStartTime: '',  // 活动开始时间
                    activityEndTime: '',    // 活动结束时间
                    activityLocation: '',   // 活动地点
                    activityAttachment: '', // 活动附件
                    status: ''  // 审核状态 1通过 :0未通过 :null待审核
                },
            ]
        }
    },
    // url:/api/activity/latestActivities
    latestActivities:{// 获取最新活动
        params:{// get参数
            pageNumber: '', // 获取页数(次数)
            pageSize: '',   // 获取数据量
            // 当pageNumber = 0， pageSize = 10 时代表获取前0 - 9个数据
            // 接下来pageNumber = 1， pageSize = 10 时代表10 - 19
            // 以此类推
        },
        data:{
            code: 31,   // 31代表获取最新活动成功
            activities:[ // 获取的多个活动
                {
                    activityId: '', // 活动id
                    activityName: '',   // 活动名
                    imageUrl: '',   // 活动宣传图
                },
            ]
        }
    },

    //post
    // url:/api/activity/newActivity
    newActivity:{// 提交新活动
        params: {// post参数
            clubId: '', // 社团标识id
            activityName: '',   // 活动名
            activityIntroduction: '',   // 活动介绍
            activityStartTime: '',  // 活动开始时间
            activityEndTime: '',    // 活动结束时间
            activityLocation: '',   // 活动地点
            applicationFormAttachment: '',  // 申请材料
            imageUrl: '',   // 活动图片
        },
        data:{
            code: 18,   //18表示发布新活动
        }
    },
    // url:/api/activity/personalPerformance
    personalPerformance:{// 提交个人活动表现
        params: {// post参数
            clubId: '', // 社团id
            activityId: '', // 活动id
            personalEffectList: [   // 个人表现
                /*学生姓名      学生学号        个人表现*/
                {stName: '', studentNumber: '', personalEffect: ''},
            ],
        },
        data:{
            code: 29    // 29代表提交个人绩效成功
        }
    },
    // url:/api/activity/activityPerformance
    activityPerformance:{// 提交活动表现
        params: {// post参数
            clubId: '', // 社团id
            activityId: '', // 活动id
            activityEffect: '', // 活动成效
        },
        data:{
            code: 30    // 30代表提交活动绩效成功
        }
    },
    // url:/api/activity/joinActivity
    joinActivity:{// 参加活动
        params: {   // post参数
            activityId: '', // 活动id
            studentNumber: '',  // 学生学号
        },
        data:{
            code: 48,   // 48代表加入活动申请
        }
    },
    /*——————————————文件——————————————*/
    // url:/api/file/uploadImage
    uploadImage:{// 上传图片
        params:{// post参数
            formData:'',    //文件url
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        },
        data:{
            code: 21,   // 21代表上传图片成功
            image:{
                url:'', // 图片地址
            }
        }
    },
    // url:/api/file/uploadFile
    uploadFile:{// 上传文件
        headers:{
          'Content-Type':'multipart/form-data'
        },
        params:{// post参数
            formData:'',    // 图片url
            flag:'' // 标签?
        },
        data:{
            code: 33,  // 33代表上传图片成功
            url:'', // 图片url
            flag:'',    // 标签？
        }
    },
}