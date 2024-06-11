import path from '../src/api/path'
import axios from 'axios'
import mockAxios from 'axios-mock-adapter'

const mock = new mockAxios(axios)

// 登录mock
mock.onPost(path.login).reply((config) => {
    const configData = config.data;
    if (configData.get("email") === 'admin@mail2.sysu.edu.cn' && configData.get("pwd") === '123456') {
        return [200, {
            code: 1, // 1代表注册/登录成功
            token: 'admin',
            studentId: 1,
        }
        ]
    }
    else {
        return [1000, {
            msg: '用户名或密码错误',
        }]
    }
})

// 注册mock
mock.onPost(path.register).reply((config) => {
    const configData = config.data;
    if (configData.get("verifyCode") === '123456') {
        return [200, {
            code: 1, // 1代表注册/登录成功
            token: 'admin',
            studentId: 1,
        }
        ]
    }
    else {
        return [1001, {
            msg: '注册失败',
        }]
    }
})

// 获取验证码mock
mock.onGet(path.verifyCode).reply((config) => {
    return [200, {
        verifyCode: '123456',
    }]
})

// 获取社团信息mock
mock.onGet(path.clubsInfo).reply((config) => {
    const data = []
    for (let i = 0; i < 20; i++) {
        const width = Math.floor(Math.random() * 100) + 300;
        const height = Math.floor(Math.random() * 100) + 400;
        data.push({
            imageUrl: `https://picsum.photos/${width}/${height}?${i}`,
            clubName: `社团${i}`,
            clubDescription: [
                {
                    title:'社团介绍',
                    content:`社团${i}是一个xxx的社团`
                },
                {
                    title:'社团描述',
                    content:`社团${i}有xxx创建`
                },
                {
                    title:'社团获奖',
                    content:`社团${i}拿了xx奖项`
                }
            ],
            mainCampus: [0, 1, 2, 3, 4][i % 5],
            totalMembers: Math.floor(Math.random() * 1000) + 1000,
            clubId: i,
            clubCategory: [0, 1, 2, 3, 4, 5][i % 6],
            createTime: '2024-4-30 12:00:00',
            clubStatus: [null, 0, 1][i % 3],
        })
    }
    console.log(data);
    return [200, {
        code: 2, // 2代表社团信息
        data: data,
    }]
})

// 获取个人信息mock
mock.onGet(path.userInfo).reply((config) => {
    return [200, {
        code: 3, // 3代表个人信息
        data: {
            stName: '张三',
            gender: '男',
            dateOfBirth: '2000-01-01',
            grade: '2019级',
            studentNumber: '2019001001',
            college: '信息科学学院',
            politicalStatus: '共青团员',
            email: 'admin@mail2.sysu.edu.cn',
            contact: '13800138000',
            hobby: '篮球',
            specialty: '计算机科学与技术',
            imageUrl: 'https://picsum.photos/300/300',
            clubs: [
                {
                    clubId: 1,
                    clubName: '社团1',
                    position: 0,
                    clubStatus: 0,
                    collegeReviewStatus: 0,
                    universityStudentUnionReviewStatus: null,
                    collegeReviewOpinion: '社团理念不行',
                    universityStudentUnionReviewOpinion: '',
                },
                {
                    clubId: 2,
                    clubName: '社团2',
                    position: 1,
                    clubStatus: 1,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 1,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '审核通过',
                },
                {
                    clubId: 3,
                    clubName: '社团3',
                    position: 2,
                    clubStatus: 1,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 1,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '审核通过',
                },
                {
                    clubId: 4,
                    clubName: '社团4',
                    position: 3,
                    clubStatus: 1,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 1,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '审核通过',
                },
                {
                    clubId: 5,
                    clubName: '社团5',
                    position: 0,
                    clubStatus: 0,
                    collegeReviewStatus: null,
                    universityStudentUnionReviewStatus: null,
                    collegeReviewOpinion: '等待学院审核',
                    universityStudentUnionReviewOpinion: '',
                },
                {
                    clubId: 6,
                    clubName: '社团6',
                    position: 0,
                    clubStatus: 0,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 0,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '已经有类似的社团了',
                },
            ],
            achievements: [
                {
                    index: 1,
                    activityName: '活动xxx', // 获得荣誉的活动名称
                    personalEffect: 'xxx活动xxx名', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    index: 2,
                    activityName: '活动xxxx', // 获得荣誉的活动名称
                    personalEffect: 'xxx活动xxxx名', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    index: 3,
                    activityName: '活动xxax', // 获得荣誉的活动名称
                    personalEffect: 'xxx活动xxx名', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
            ]
        },
    }]
})

// 更新个人信息mock
mock.onPost(path.userInfoUpdate).reply((config) => {
    return [200, {
        code: 4, // 4代表个人信息更新
    }]
})

// 社团申请mock
mock.onPost(path.newClubApply).reply((config) => {
    return [200, {
        code: 5, // 5代表社团申请
    }]
})

// 获取社团活动通知mock
mock.onGet(path.clubActAndNtc).reply((config) => {
    const activities = []
    const notices = []
    for (let i = 0; i < 20; i++) {
        activities.push({
            activityId: i,
            activityName: '社团' + config.params.clubId + '的活动' + i,
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://picsum.photos/300/300" ><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            activityStartTime: '2024-04-29 00:00:00',
            activityEndTime: '2024-05-03 00:00:00',
            activityLocation: '活动' + i + '地点',
            imageUrl: `https://picsum.photos/400/300?${i}`,
        })
        notices.push({
            announcementId: i,
            title: '社团' + config.params.clubId + '的通知' + i,
            content: '通知' + i + '介绍',
            imageUrl: `https://picsum.photos/400/300?${i}`,
            publishTime: '2024-04-29 00:00:00',
        })
    }
    return [200, {
        code: 6, // 6代表获取社团活动通知
        clubId: config.params.clubId,
        activities: activities,
        notices: notices
    }]
})

// 获取社团简介mock
mock.onGet(path.clubIntroduction).reply((config) => {
    return [200, {
        code: 7, // 7代表获取社团简介
        clubDescription: [
            {
                title: '社团成立',
                content: '<p>2020年6月，中山大学在珠海校区整建制成立软件工程学院。学院以立德树人为人才培养根本目标，以“全员、全过程、全方位”的育人模式，鼓励学生主动探索、积极思考、勇于实践，培养科学素养和创新精神，培育引领新一轮科技革命的卓越软件工程人才。</p><p>学院软件工程专业为独立一级学科，是国家一流本科专业建设点。软件工程学院在人工智能、大数据、云计算、区块链等多个领域进行重点学科布局，围绕可信软件、软件服务工程、领域软件工程三大板块，聚合形成了包括区块链、物联网、大模型软件、金融软件、医疗软件、软件可靠性等在内的学科特色。</p><img src="https://sse.sysu.edu.cn/sites/sse.prod.dpcms4.sysu.edu.cn/files/inline-images/1_4.png" /><img src="https://sse.sysu.edu.cn/sites/sse.prod.dpcms4.sysu.edu.cn/files/inline-images/image_45.png" />',
            },
            {
                title: '社团介绍',
                content: '<p>软件工程专业是2002年国家教育部新增专业，随着计算机应用领域的不断扩大及中国经济建设的不断发展，软件工程专业成为一个新的热门工科专业。</p><img src="https://sse.sysu.edu.cn/sites/sse.prod.dpcms4.sysu.edu.cn/files/inline-images/image_45.png" />',
            },
            {
                title: '社团成就',
                content: '<img src="https://sse.sysu.edu.cn/sites/sse.prod.dpcms4.sysu.edu.cn/files/inline-images/image_45.png" />'
            }
        ]
    }]
})

// 获取社团成员mock
mock.onGet(path.clubMembers).reply((config) => {
    return [200, {
        code: 8, // 8代表获取社团成员
        members: {
            president: {
                stName: '张三',
                position: 0,
                contact: '12345678901',
                studentNumber: '201900001',
                imageUrl: 'https://picsum.photos/300/300?0',
            },
            executives: [
                {
                    stName: '李四',
                    position: 1,
                    studentNumber: '201900002',
                    imageUrl: 'https://picsum.photos/300/300?1',
                },
                {
                    stName: '王五',
                    position: 2,
                    studentNumber: '201900003',
                    imageUrl: 'https://picsum.photos/300/300?2',
                },
                {
                    stName: '赵六',
                    position: 2,
                    studentNumber: '201900004',
                    imageUrl: 'https://picsum.photos/300/300?3',
                }
            ],
            others: {
                totalMembers: 100,
            }
        }
    }]
})

// 管理员登录mock
mock.onPost(path.postAdminLogin).reply((config) => {
    const configData = config.data;
    if (configData.get("account") === 'administer' && configData.get("password") === '123456') {
        return [200,{
            code: 9, // 9代表管理员登录成功
            token: 'admin',
            uid: 1,
        }]
    }
    else {
        return [1000, {
            msg: '用户名或密码错误',
        }]
    }
})

// 获取管理员信息mock
mock.onGet(path.adminInfo).reply((config) => {
    return [200, {
        code: 10, // 10代表管理员信息
        data: {
            adminId: '12345678',
            account: 'administer',
            sort: 0, //0代表学院管理员,1代表校级管理员
            password: '123456',
            contact: '37037037037',
            affiliatedUnit: '软件工程',
            clubs: [//这是学院管理员管理的所有社团
                {
                    clubId: 1,
                    clubName: '篮球社',
                    establishedTime: '2024/05/03',
                    affiliatedUnitId: '7432',
                    clubIntroduction: '打篮球的社团。',
                    clubSort: '0（枚举类，对应体育类）',//0，1，2，3，4
                    clubStatus: '这是社团1状态的枚举',
                    administrativeAdvisorName: '刑震',
                    businessAdvisorName: '叶悟',
                    contactsId: '21314151',
                    location: '广州南校',
                    totalMembership: '40',
                    financePublicity: '公开'
                },
                {
                    clubId: 2,
                    clubName: '社团2',
                    establishedTime: '2023/06/05',
                    affiliatedUnitId: '这是社团2附属部门id',
                    clubIntroduction: '这是社团2简介',
                    clubSort: '这是社团2类别的枚举',//0，1，2，3，4
                    clubStatus: '这是社团2状态的枚举',
                    administrativeAdvisorName: '这是行政指导老师姓名',
                    businessAdvisorName: '这是业务指导老师姓名',
                    contactsId: '这是社团2联系人Id',
                    location: '珠海校区',
                    totalMembership: '这是社团2总人数',
                    financePublicity: '不公开'
                }
            ],
        },

    }]
})

// 更新管理员信息mock
mock.onPost(path.adminInfoUpdate).reply((config) => {
    return [200, {
        code: 11, // 11代表管理员信息更新
    }]
})


// 获取活动信息mock
mock.onGet(path.activityInfo).reply((config) => {
    return [200, {
        code: 12, // 12代表获取活动信息
        data: {
            activityId: 1,
            clubName: '社团名称',
            activityName: '活动名称',
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://picsum.photos/300/300" alt="活动配图1"><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            activityPublishTime: '2023-04-19 12:00:00',
            activityStartTime: '2023-04-20 12:00:00',
            activityEndTime: '2023-04-20 18:00:00',
            activityLocation: '活动地点',
            activityAttachment: "file",
            contactPerson:'小航',
            contactPhone:'12843251',
            status: 1
        }
    }]
})

mock.onGet(path.activitiesInfo).reply((config) => {
    const data = []
    for (let i = 0; i < 15; i++) {
        data.push({
            activityId: i,
            clubName: `社团${i}`,
            activityName: '活动名称',
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://picsum.photos/300/300" alt="活动配图1"><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            createTime: '2023-04-19 12:00:00',
            activityStartTime: '2023-04-20 12:00:00',
            activityEndTime: '2023-04-20 18:00:00',
            activityLocation: '活动地点',
            activityStatus: [null, 0, 1][i % 3],
        })
    }
    return [200, {
        code: 13, // 13代表获取全部活动信息
        data: data,
    }]
})

// 获取社团活动列表mock
mock.onGet(path.clubActivityList).reply((config) => {
    const activityList = []
    const pageSize = config.params.pageSize
    const pageNumber = config.params.pageNumber
    for (let i = pageNumber * pageSize; i < (pageNumber + 1) * pageSize; i++) {
        activityList.push({
            activityId: i,
            activityName: `活动名称${i}`,
            imageUrl: `https://picsum.photos/400/300?`,
            status: i % 3, //此处需要区分活动审批状态和活动进行状态
            activityPlace: ['珠海校区新体育馆', '校外区域', '广州南校英东体育场', '广州南校新体育馆',][i % 4],
            activityEffect:['无内容','活动成效描述1','活动成效描述2',][i%3]//活动成效
        })
    }
    return [200, {
        code: 14, // 14代表获取社团活动列表
        activityList,
    }]
})

// 获取社团申请列表mock
mock.onGet(path.clubApplyList).reply((config) => {
    const applyList = []
    for (let i = 0; i < 15; i++) {
        applyList.push({
            studentId: i,
            stName: '张三',
            college: '计算机学院',
            politicalStatus: '共青团员',
            status: [null, 0, 1][i % 3],
            email: '123@qq.com',
            createTime: '2023-04-20 12:00:00',
            reason: '我是张三，我申请加入计算机学院',
        })
    }
    return [200, {
        code: 15, // 15代表获取社团申请列表
        applyList,
    }]
})

// 同意社团申请mock
mock.onPost(path.agreeClubApply).reply((config) => {
    const data = JSON.parse(config.data)
    return [200, {
        code: 16, // 16代表同意社团申请
        studentId: data.params.studentId,
    }]
})

// 拒绝社团申请mock
mock.onPost(path.rejectClubApply).reply((config) => {
    const data = JSON.parse(config.data)
    return [200, {
        code: 17, // 17代表拒绝社团申请
        studentId: data.params.studentId,
    }]
})

// 发布新活动mock
mock.onPost(path.newActivity).reply((config) => {
    console.log(JSON.parse(config.data));
    return [200, {
        code: 18, // 18代表发布新活动
    }]
})

// 社团骨干评优mock
mock.onPost(path.backBoneEvaluate).reply((config) => {
    return [200, {
        code: 19, // 19代表社团骨干评优
    }]
})

// // 社团信息mock
// mock.onGet(path.clubEvaluateInfo).reply((config) => {
//     return [200, {
//         code: 20, // 20代表获取社团评优所需信息
//         clubName: 'xx社团',
//         totalMembers: 100,
//         isFinancialInformationPublic: 1,
//         administrativeGuideTeacherName: '张老师',
//         businessGuideTeacherName: '李老师',
//         backboneNumber: 10,
//         establishmentDate: '2022-05-01',
//         responsibleDepartment: '体育部',
//         mainCompus: 3,
//         clubDescription: '篮球社是一个篮球社团',
//         clubCategory: 2,
//         contactPerson: '张三',
//         politicalStatus: '党员',
//         contactPhone: '123456789',
//         clubStatus: 1,
//         file: 'file',
//         communistRelatedBackBoneNumber: 10,
//         publicityManagementInfo: {
//             WeChatPublicAccount: { name: '哈哈哈', submitCount: '20', Auditors: { name1: '张三', name2: '李四', name3: '王五' } },
//             WebSite: { has: '1', name: '网站' },
//             Blog: { has: '1', name: '微博' },
//             BiliBili: { has: '1', name: 'b站' },
//             TikTok: { has: '1', name: '抖音' },
//             WeChatVideoAccount: { has: '1', name: '微信视频号' },
//             KuaiShou: { has: '1', name: '快手' },
//             Publication: { has: '1', name: '刊物' },
//             Other: { has: '1', name: '其他' },
//         },
//     }]
// })

// 上传图片mock
mock.onPost(path.uploadImage).reply((config) => {
    // 后端通过config.params.formData.get('image')获取到图片信息
    return [200, {
        code: 21, // 21代表上传图片成功
        image: {
            url: 'https://tdesign.gtimg.com/demo/demo-image-1.png',
            flag: config.params.flag
        }
    }]
})

// 删除社团干部mock
mock.onPost(path.deleteClubMember).reply((config) => {
    return [200, {
        code: 22, // 22代表删除社团干部成功
    }]
})

// 更新社团干部信息mock
mock.onPost(path.updateClubMember).reply((config) => {
    return [200, {
        code: 23, // 23代表更新社团干部信息成功
    }]
})

// 添加社团干部mock
mock.onPost(path.addClubMember).reply((config) => {
    return [200, {
        code: 24, // 24代表添加社团干部成功
    }]
})

// 更新社团信息mock
mock.onPost(path.updateClubInfo).reply((config) => {
    return [200, {
        code: 25, // 25代表更新社团信息成功
    }]
})

// 获取骨干评审信息mock
mock.onGet(path.backBoneEvaluations).reply((config) => {
    const data = [];
    for (let i = 0; i < 10; i++) {
        data.push({
            recordId: i,
            stName: ["小红", "小强", "小王"][i % 3],
            studentNumber: [1, 2, 3][i % 3],
            contact: '114514',
            declarationYear:[2021,2022,2023][i % 3],
            college: ["人工智能", "软件工程", "测绘"][i % 3],
            politicalStatus: ["群众", "党员"][i % 2],
            clubName: ["篮球社", "围棋社", "街舞社"][i % 3],
            position: ["社长", "副社长"][i % 2],
            tenurePeriod: '100',
            achievements: {
                gpa: '4.0/5',
                rank: '1/122',
                rankRatio: '0.82%',
            },
            trainingParticipation: [
                { time: '2022', location: '党校', organization: '学院' },
            ],
            associationAwards: [
                { name: 'xx奖项', time: '2022', organization: '学院' },
            ],
            awards: [
                { name: 'xx个人奖项', time: '2022', organization: '学院' },
            ],
            clubWorkStatus: '工作描述',
            status: null
        })
    }
    return [200, {
        code: 26, // 26代表获取骨干申请成功
        data,
    }]
})

//所有社团年审
mock.onGet(path.clubAnnuals).reply((config) => {
    const data = [];
    for (let i = 0; i < 20; i++) {
        data.push({
            declarationId: i,
            declarationYear: ["2020", "2021", "2022"][i % 3],
            clubName: ["篮球社", "围棋社", "街舞社"][i % 3],
            status:[null, 0, 1][i % 3],
        })
    }
    return [200, {
        code: 27, // 27代表获取社团年审成功
        data,
    }]
})

mock.onGet(path.clubEvaluations).reply((config) => {
    const data = [];
    for (let i = 0; i < 10; i++) {
        data.push({
            recordId: i,
            declarationYear: ["2021", "2022", "2023"][i % 3],
            clubId: i,
            clubName: "xx社团",
            handoverMethod: [0, 1][i % 2],//0全员大会,1骨干例会
            handoverParticipantsCount: 100,
            guideTeacher: [0, 1][i % 2],
            meetings: [
                {
                    activityId: 0,
                    clubId: 0,
                    time: "2022-1-1",
                    location: "会议地点",
                    staffMeetingOrbackBoneMeeting: 0,//0全员大会,1骨干例会
                    guideTeacher: 1,
                }
            ],
            associationAwards: [
                {
                    name: "xx奖",
                    time: "2022-02-02",
                    organization: "中山大学"
                }
            ],
            publicityManagementEffectiveness: {
                submissionsCount: 5,
                PublicityAboveSchoolLevel: [
                    {
                        platform: "平台",
                        content: "内容",
                    }
                ],
            },
            hostedSchoolLevelActivities: {
                schoolLv: [
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ],
                municipal: [
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ],
                provincial: [
                    {
                        host: "主办方",
                        activityName: "活动名",
                    }
                ]
            },
            activities: [
                {
                    activityName: "活动名称",
                    activityTime: "活动时间",
                    activityEffect: "活动成效",
                }
            ],
            clubWorkIntroduction: "工作简介"
        })
    }
    return [200, {
        code: 28, // 28代表获取社团评优成功
        data,
    }]
})

// 提交个人绩效mock
mock.onPost(path.personalPerformance).reply((config) => {
    return [200, {
        code: 29, // 29代表提交个人绩效成功
    }]
})

// 提交活动绩效mock
mock.onPost(path.activityPerformance).reply((config) => {
    return [200, {
        code: 30, // 30代表提交活动绩效成功
    }]
})

// 获取最新活动mock
mock.onGet(path.latestActivities).reply((config) => {
    const data = []
    const pageSize = config.params.pageSize
    const pageNumber = config.params.pageNumber
    for (let i = 0; i < pageSize; i++) {
        data.push({
            activityId: i,
            activityName: '活动名称' + pageNumber + i,
            imageUrl: `https://picsum.photos/400/300`,
        })
    }
    return [200, {
        code: 31, // 31代表获取最新活动成功
        data,
    }]
})

// 获取十佳社团mock
mock.onGet(path.topTenClubs).reply((config) => {
    const data = []
    for (let i = 0; i < 10; i++) {
        data.push({
            clubId: i,
            clubName: '十佳社团' + i,
            imageUrl: `https://picsum.photos/400/300?${i}`,
        })
    }
    return [200, {
        code: 32, // 32代表获取十佳社团成功
        data,
    }]
})

// 上传文件mock
mock.onPost(path.uploadFile).reply((config) => {
    // 后端通过config.params.formData.get('file')获取到文件信息
    return [200, {
        code: 33, // 33代表上传文件成功
        file: {
            url: 'https://tdesign.gtimg.com/demo/demo-image-1.png',
            flag: config.params.flag
        }
    }]
})

// 提交社团年审表mock
mock.onPost(path.clubAnnualAuditForm).reply((config) => {
    return [200, {
        code: 34, // 34代表提交社团年审表成功
    }]
})

// 提交社团评优表mock
mock.onPost(path.clubEvaluationForm).reply((config) => {
    return [200, {
        code: 35, // 35代表提交社团评优表成功
    }]
})

// 获取社团通知列表mock
mock.onGet(path.clubNoticeList).reply((config) => {
    const notices = []
    for (let i = 0; i < 10; i++) {
        notices.push({
            announcementId: i,
            title: "标题" + i,
            content: "内容" + i,
            imageUrl: `https://picsum.photos/400/300?${i}`,
            publishTime: "2022-01-01 00:00:00",
            modifyTime: "2022-01-01 00:00:00",
        })
    }
    return [200, {
        code: 36, // 36代表获取社团通知列表成功
        notices,
    }]
})

// 提交新通知mock
mock.onPost(path.newNotice).reply((config) => {
    return [200, {
        code: 37, // 37代表提交新通知成功
    }]
})

// 发布新会议mock
mock.onPost(path.newMeeting).reply((config) => {
    return [200, {
        code: 38, // 38代表发布新会议成功
    }]
})

// 获取图片验证码mock
mock.onGet(path.getImageVerifyCode).reply((config) => {
    return [200, {
        code: 39, // 39代表获取图片验证码成功
        imageUrl: 'src/assets/verify.jpg' // 连接后端之后再做具体处理
    }]
})

// 更新社团简介mock
mock.onPost(path.updateClubDescription).reply((config) => {
    return [200, {
        code: 40, // 40代表更新社团简介成功
    }]
})

// 获取枚举列表mock
mock.onGet(path.getEnumList).reply((config) => {
    console.log(1111111111);
    return [200, {
        code: 41, // 41代表获取枚举列表成功
        data: {
            "positions": [
                { "name": "社长", "code": 0 },
                { "name": "副社长", "code": 1 },
                { "name": "干部", "code": 2 },
                { "name": "普通成员", "code": 3 }
            ],
            "mainCampuses": [
                { "name": "广州北校园", "code": 0 },
                { "name": "广州南校园", "code": 1 },
                { "name": "广州东校园", "code": 2 },
                { "name": "珠海校区", "code": 3 },
                { "name": "深圳校区", "code": 4 }
            ],
            "clubCategories": [
                { "name": "学术类", "code": 0 },
                { "name": "体育类", "code": 1 },
                { "name": "艺术类", "code": 2 },
                { "name": "公益类", "code": 3 },
                { "name": "科技类", "code": 4 },
                { "name": "其它类", "code": 5 }
            ],
            "meetings": [
                { "name": "骨干例会", "code": 0 },
                { "name": "全员大会", "code": 1 },
                { "name": "其他", "code": 2 },
            ]
        }
    }]
})

//获取特定社团的骨干评优记录
mock.onGet(path.myClubBackboneExamData).reply((config) => {
    let cid = config.clubId;
    //根据clubId返回骨干评优信息：
    const returnData = [];
    for (let i = 0; i < 3; i++) {
        returnData.push({
            stName: '张三',
            status: i % 3,
            recordId: i,
            reviewOpinion: '评优通过',//此处可以返回任职时间
            declarationYear: [2022,2023,2024][i%3],
        })
    }
    return [200, {
        code: 42, // 42代表获取社团年审信息成功
        returnData,
    }]
})

//获取特定社团的年审记录
mock.onGet(path.myClubAnnualExamData).reply((config) => {
    let cid = config.clubId;
    //根据clubId返回年审信息：
    const returnData = [];
    for (let i = 0; i < 3; i++) {
        returnData.push({
            clubName: '篮球社',
            status: i % 3,
            declarationId: i,
            reviewOpinion: '备注为空',//此处可以返回部门意见
            declarationYear: [2022,2023,2024][i%3],
        })
    }
    return [200, {
        code: 43, // 43代表获取社团年审信息成功
        returnData,
    }]
})

//获取特定社团的教师评优记录
mock.onGet(path.myClubTeacherExamData).reply((config) => {
    let cid = config.clubId;
    //根据clubId返回教师评优记录：
    const returnData = [];
    for (let i = 0; i < 3; i++) {
        returnData.push({
            teacherName: '刘华强',
            status:i % 3,
            declarationYear: [2022,2023,2024][i%3],
            declarationId: i,
            reviewOpinion: '是个好老师',//此处可以返回评优意见
        })
    }
    return [200, {
        code: 44, // 44代表获取社团教师评优记录成功
        returnData,
    }]
})

// 获取社团获奖信息mock
mock.onGet(path.associationAwards).reply((config) => {
    const associationAwards = []
    for (let i = 0; i < 3; i++) {
        associationAwards.push({
            awardName: i + "活动一等奖",
            awardTime: "2022-01-01",
            issuingAuthority: "广东省教育厅",
        })
    }
    return [200, {
        code: 45, // 45代表获取社团获奖信息成功
        associationAwards
    }]
})

// 获取社团会议
mock.onGet(path.meetings).reply((config) => {
    return [200, {
        code: 46, // 46代表获取社团会议信息成功
        meetings: [
            {
                meetingTime: "2022-01-01",
                location: "广州校区",
                category: 0,
            },
            {
                meetingTime: "2022-01-01",
                location: "广州校区",
                category: 1,
            },
            {
                meetingTime: "2022-01-01",
                location: "线上",
                category: 0,
            },
        ]
    }]
})

// 加入社团mock
mock.onPost(path.joinClub).reply((config) => {
    return [200, {
        code: 47, // 47代表加入社团申请
    }]
})

// 加入活动mock
mock.onPost(path.joinActivity).reply((config) => {
    return [200, {
        code: 48, // 48代表加入活动申请
    }]
})

mock.onGet(path.clubApplicationInfo).reply((config)=>{
    const data = {
        recordId:'1',
        clubId:'1',
        clubName: 'xx社团',
        establishmentDate: '2024-06-05',
        responsibleDepartment: '学院',
        mainCampus: '珠海校区',
        clubDescription: '描述',
        clubCategory: '1',
        administrativeGuideTeacherName: '张老师',
        businessGuideTeacherName: '李老师',
        contactPerson: '小明',
        contactPhone: '1864321546',
        clubStatus: '1',
        attachmentUrl: 'url',
        advisorResumeAttachmentUrl:'url',
    }
    return[200,{
        code: 49, // 获取建立社团申请详细信息
        data
    }]
})

mock.onGet(path.clubAnnual).reply((config)=>{
    const data = {
        clubName: 'hh',
        clubCategory: 1,
        mainCampus: 1,
        totalMembers: '12',
        meetingActivityListAttachment: '会议活动附件',
        externalsponsorshipAttachment:'校外赞助附件',
        clubConstitutionAttachment:'社团章程附件',
        administrativeGuideTeacherName: 'hh',
        businessGuideTeacherName: 'hh',
        establishmentDate: 't',
        contactPerson: 't',
        contactPhone: '1',
        clubStatus: '1',
        responsibleDepartment: '学院',
        politicalStatus: '1',
        isFinancialInformationPublic: '1',
        publicityManagementInfo: {
            WeChatPublicAccount: { name: 'hh', submitCount: '12', Auditors: { name1: 'hh', name2: 'hh', name3: 'hh' } },
            WebSite: { has: '1', name: '2' },
            Blog: { has: '1', name: '2' },
            BiliBili: { has: '1', name: '2' },
            TikTok: { has: '1', name: '2' },
            WeChatVideoAccount: { has: '1', name: '2' },
            KuaiShou: { has: '1', name: '2' },
            Publication: { has: '1', name: '2' },
            Other: { has: '1', name: '2' },
        },
    }
    return[200,{
        code: 50, // 获取社团年审详细信息
        data
    }]
})

mock.onGet(path.clubAward).reply((config)=>{
    const data = {
        clubName:'xx社团',
        totalMembers:'100',
        backboneNumber:'10',
        communistRelatedBackBoneNumber:'5',
        administrativeGuideTeacherName:'张老师',
        businessGuideTeacherName:'李老师',
        isFinancialInformationPublic:'0',
        imageUrl: 'url',
        clubEducationCaseAttachment:'育人案例'
    }
    return[200,{
        code: 51,
        data
    }]
})

mock.onGet(path.clubInfo).reply((config)=>{
    const data={
        clubName:'xx社团',
        establishmentDate:'2021/06/05',
        clubCategory:'1',
        responsibleDepartment:'1',
        administrativeGuideTeacherName:'李老师',
        businessGuideTeacherName:'张老师',
        mainCompus:'0',
        totalMembers:'100',
        isFinancialInformationPublic:'1',
        imageUrl:'url',
    }
    return[200, {
        code: 52,
        data
    }]
})

mock.onPost(path.passClubApproval).reply((config)=>{
    return[200,{
        code: 53,
    }]
})

mock.onPost(path.unPassClubApproval).reply((config)=>{
    return[200,{
        code: 54,
    }]
})
mock.onPost(path.passActivityApproval).reply((config)=>{
    return[200,{
        code: 55,
    }]
})

mock.onPost(path.unPassActivityApproval).reply((config)=>{
    return[200,{
        code: 56,
    }]
})
mock.onPost(path.passBackboneAwardsReview).reply((config)=>{
    return[200,{
        code: 57,
    }]
})

mock.onPost(path.unPassBackboneAwardsReview).reply((config)=>{
    return[200,{
        code: 58,
    }]
})

mock.onGet(path.thisYearClubAnnual).reply((config)=>{
    const data = {
        clubName: 'hh',
        clubCategory: '1',
        mainCompus: '1',
        clubDescription: 'hh',
        totalMembers: '12',
        meetingActivityListAttachment: '会议活动附件',
        externalsponsorshipAttachment:'校外赞助附件',
        clubConstitutionAttachment:'社团章程附件',
        administrativeGuideTeacherName: 'hh',
        businessGuideTeacherName: 'hh',
        establishmentDate: 't',
        contactPerson: 't',
        contactPhone: '1',
        clubStatus: '1',
        responsibleDepartment: '学院',
        politicalStatus: '1',
        isFinancialInformationPublic: '1',
        publicityManagementInfo: {
            WeChatPublicAccount: { name: 'hh', submitCount: '12', Auditors: { name1: 'hh', name2: 'hh', name3: 'hh' } },
            WebSite: { has: '1', name: '2' },
            Blog: { has: '1', name: '2' },
            BiliBili: { has: '1', name: '2' },
            TikTok: { has: '1', name: '2' },
            WeChatVideoAccount: { has: '1', name: '2' },
            KuaiShou: { has: '1', name: '2' },
            Publication: { has: '1', name: '2' },
            Other: { has: '1', name: '2' },
        },
    }
    return[200,{
        code: 59, // 获取今年社团年审详细信息
        data
    }]
})

mock.onPost(path.passClubAnnualReview).reply((config)=>{
    return[200,{
        code: 60,
    }]
})

mock.onPost(path.unPassClubAnnualReview).reply((config)=>{
    return[200,{
        code: 61,
    }]
})

mock.onPost(path.passClubAwardReview).reply((config)=>{
    return[200,{
        code: 62,
    }]
})

mock.onPost(path.unPassClubAwardReview).reply((config)=>{
    return[200,{
        code: 63,
    }]
})
