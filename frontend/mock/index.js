import path from '../src/api/path'
import axios from 'axios'
import mockAxios from 'axios-mock-adapter'

const mock = new mockAxios(axios)

// 登录mock
mock.onPost(path.login).reply((config) => {
    const configData = JSON.parse(config.data);
    if (configData.email === 'admin@mail2.sysu.edu.cn' && configData.pwd === '123456') {
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
    if (configData.verifyCode === '123456') {
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
    for (let i = 0; i < 10; i++) {
        const width = Math.floor(Math.random() * 100) + 300;
        const height = Math.floor(Math.random() * 100) + 400;
        data.push({
            imageUrl: `https://loremflickr.com/${width}/${height}?${i}`,
            clubName: `社团${i}`,
            clubDescription: `社团${i}介绍`,
            totalMembers: Math.floor(Math.random() * 1000) + 1000,
            clubId: i,
            clubCategory: '体育类',
        })
    }

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
            activityIntroduction: '活动' + i + '介绍',
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
                imageUrl: 'https://loremflickr.com/300/300',
            },
            executives: [
                {
                    stName: '李四',
                    position: '副社长',
                    imageUrl: 'https://loremflickr.com/300/300',
                },
                {
                    stName: '王五',
                    position: '干部',
                    imageUrl: 'https://loremflickr.com/300/300',
                },
                {
                    stName: '赵六',
                    position: '干部',
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
    if (configData.adminId === 'administer' && configData.password === '123456') {
        //console.log("管理员登录：mock部分成功")
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
            activityName: '活动名称',
            activityIntroduction: '<p>精彩活动即将开始！</p><img src="https://loremflickr.com/300/300" alt="活动配图1"><p>亲爱的朋友们，我们非常高兴地宣布即将举办一场令人兴奋的活动！这将是一个不容错过的机会，我们诚邀您的参与。</p><ul><li>精彩演讲：我们邀请了行业内的顶尖专家，将为您带来激动人心的演讲和见解。</li><li>互动工作坊：参与我们的工作坊，与其他行业同仁交流经验，共同解决挑战。</li><li>展览展示：活动期间将有多个展览区域，展示最新的技术和产品。</li></ul><p>谢谢您的关注和支持，我们期待在活动中与您见面！</p>',
            activityStartTime: '2023-04-20 12:00:00',
            activityEndTime: '2023-04-20 18:00:00',
            activityLocation: '活动地点',
        }
    }]
})