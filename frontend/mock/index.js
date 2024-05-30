import path from '../src/api/path'
import axios from 'axios'
import mockAxios from 'axios-mock-adapter'

const mock = new mockAxios(axios)

// 登录mock
mock.onPost(path.login).reply((config) => {
    const configData = JSON.parse(config.data);
    if (configData.params.email === 'admin@mail2.sysu.edu.cn' && configData.params.pwd === '123456') {
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
    const configData = JSON.parse(config.data);
    if (configData.params.verifyCode === '123456') {
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
            imageUrl: `https://loremflickr.com/${width}/${height}?${i}`,
            clubName: `社团${i}`,
            clubDescription: `社团${i}介绍`,
            campus: ["北校区", "南校区", "东校区", "珠海校区", "深圳校区"][i % 5],
            totalMembers: Math.floor(Math.random() * 1000) + 1000,
            clubId: i,
            clubCategory: ["学术类", "体育类", "艺术类", "公益类", "科技类", "其他类"][i % 6],
            createTime: '2024-4-30 12:00:00',
            status: i % 3,
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
            clubs: [
                {
                    clubId: 1,
                    clubName: '社团1',
                    position: '社长',
                    clubStatus: 0,
                    collegeReviewStatus: 0,
                    universityStudentUnionReviewStatus: null,
                    collegeReviewOpinion: '社团理念不行',
                    universityStudentUnionReviewOpinion: '',
                },
                {
                    clubId: 2,
                    clubName: '社团2',
                    position: '副社长',
                    clubStatus: 1,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 1,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '审核通过',
                },
                {
                    clubId: 3,
                    clubName: '社团3',
                    position: '干部',
                    clubStatus: 1,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 1,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '审核通过',
                },
                {
                    clubId: 4,
                    clubName: '社团4',
                    position: '普通成员',
                    clubStatus: 1,
                    collegeReviewStatus: 1,
                    universityStudentUnionReviewStatus: 1,
                    collegeReviewOpinion: '审核通过',
                    universityStudentUnionReviewOpinion: '审核通过',
                },
                {
                    clubId: 5,
                    clubName: '社团5',
                    position: '社长',
                    clubStatus: 0,
                    collegeReviewStatus: null,
                    universityStudentUnionReviewStatus: null,
                    collegeReviewOpinion: '等待学院审核',
                    universityStudentUnionReviewOpinion: '',
                },
                {
                    clubId: 6,
                    clubName: '社团6',
                    position: '社长',
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
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://loremflickr.com/300/300" ><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            activityStartTime: '2024-04-29 00:00:00',
            activityEndTime: '2024-05-03 00:00:00',
            activityLocation: '活动' + i + '地点',
            imageUrl: `https://loremflickr.com/400/300?${i}`,
        })
        notices.push({
            announcementId: i,
            title: '社团' + config.params.clubId + '的通知' + i,
            content: '通知' + i + '介绍',
            imageUrl: `https://loremflickr.com/400/300?${i}`,
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
                position: '社长',
                contact: '12345678901',
                studentNumber: '201900001',
                imageUrl: 'https://loremflickr.com/300/300',
            },
            executives: [
                {
                    stName: '李四',
                    position: '副社长',
                    studentNumber: '201900002',
                    imageUrl: 'https://loremflickr.com/300/300',
                },
                {
                    stName: '王五',
                    position: '干部',
                    studentNumber: '201900003',
                    imageUrl: 'https://loremflickr.com/300/300',
                },
                {
                    stName: '赵六',
                    position: '干部',
                    studentNumber: '201900004',
                    imageUrl: 'https://loremflickr.com/300/300',
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
    const configData = JSON.parse(config.data);
    if (configData.params.adminId === 'administer' && configData.params.password === '123456') {
        console.log("管理员登录：mock部分成功")
        return [200, {
            code: 9, // 9代表管理员登录成功
            token: 'admin',
            uid: 1,
        }
        ]
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
            clubs: {//这是社团管理员所管理的唯一社团，因此不用[]，直接用{}
                clubId: 1,
                clubName: '社团1',
                establishedTime: '2024/05/03',
                affiliatedUnitId: '这是附属部门id',
                clubIntroduction: '这是社团简介',
                clubSort: '这是社团类别的枚举',//0，1，2，3，4
                clubStatus: '这是社团状态的枚举',//0未通过、1通过
                administrativeAdvisorName: '这是行政指导老师姓名',
                businessAdvisorName: '这是业务指导老师姓名',
                contactsId: '这是联系人Id',
                location: '广州南校',
                totalMembership: '这是社团总人数',
                financePublicity: '这是是否成员财务公开'
            },

            activitiesHistory: [
                {
                    activityName: '活动1', // 获得荣誉的活动名称
                    award: 'xxx', // 荣誉名称
                    awardWiningTime: '2024-04-03 00:00:00', // 获奖时间
                },
                {
                    activityName: '活动2', // 获得荣誉的活动名称
                    award: 'xxx', // 荣誉名称
                    awardWiningTime: '2024-05-04 00:00:00', // 获奖时间
                },
                {
                    activityName: '活动3', // 获得荣誉的活动名称
                    award: 'xxx', // 荣誉名称
                    awardWiningTime: '2024-05-05 00:00:00', // 获奖时间
                },
            ]
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
        activity: {
            activityId: 1,
            clubName: '社团名称',
            activityName: '活动名称',
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://loremflickr.com/300/300" alt="活动配图1"><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            createTime: '2023-04-19 12:00:00',
            activityStartTime: '2023-04-20 12:00:00',
            activityEndTime: '2023-04-20 18:00:00',
            activityLocation: '活动地点',
            activityAttachment: "file",
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
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://loremflickr.com/300/300" alt="活动配图1"><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            createTime: '2023-04-19 12:00:00',
            activityStartTime: '2023-04-20 12:00:00',
            activityEndTime: '2023-04-20 18:00:00',
            activityLocation: '活动地点',
            status: i % 3,
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
    for (let i = 0; i < pageSize; i++) {
        activityList.push({
            activityId: i,
            activityName: `活动名称${i}`,
            imageUrl: `https://loremflickr.com/400/300`,
            status: i % 3,
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
            status: i % 3,
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

// 社团信息mock
mock.onGet(path.clubEvaluateInfo).reply((config) => {
    return [200, {
        code: 20, // 20代表获取社团评优所需信息
        clubName: 'xx社团',
        totalMembers: 100,
        isFinancialInformationPublic: 1,
        administrativeGuideTeacherName: '张老师',
        businessGuideTeacherName: '李老师',
        backboneNumber: 10,
        establishmentDate: '2022-05-01',
        responsibleDepartment: '体育部',
        mainCompus: '北校区',
        clubDescription: '篮球社是一个篮球社团',
        clubCategory: '体育类',
        contactPerson: '张三',
        politicalStatus: '党员',
        contactPhone: '123456789',
        clubStatus: 1,
        file: 'file',
        communistRelatedBackBoneNumber: 10,
        publicityManagementInfo: {
            WeChatPublicAccount: { name: '哈哈哈', submitCount: '20', Auditors: { name1: '张三', name2: '李四', name3: '王五' } },
            WebSite: { has: '1', name: '网站' },
            Blog: { has: '1', name: '微博' },
            BiliBili: { has: '1', name: 'b站' },
            TikTok: { has: '1', name: '抖音' },
            WeChatVideoAccount: { has: '1', name: '微信视频号' },
            KuaiShou: { has: '1', name: '快手' },
            Publication: { has: '1', name: '刊物' },
            Other: { has: '1', name: '其他' },
        },
    }]
})

// 上传图片mock
mock.onPost(path.uploadImage).reply((config) => {
    return [200, {
        code: 21, // 21代表上传图片成功
        image: {
            url: 'https://tdesign.gtimg.com/demo/demo-image-1.png',
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
        })
    }
    return [200, {
        code: 26, // 26代表获取骨干申请成功
        data,
    }]
})

mock.onGet(path.clubAnnuals).reply((config) => {
    const data = [];
    for (let i = 0; i < 20; i++) {
        data.push({
            declarationId: i,
            declarationYear: ["2020", "2021", "2022"][i % 3],
            clubName: ["篮球社", "围棋社", "街舞社"][i % 3],
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