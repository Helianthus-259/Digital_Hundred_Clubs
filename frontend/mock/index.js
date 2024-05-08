import path from '../src/api/path'
import axios from 'axios'
import mockAxios from 'axios-mock-adapter'

const mock = new mockAxios(axios)

// 登录mock
mock.onPost(path.login).reply((config) => {
    const configData = JSON.parse(config.data);
    if (configData.email === 'admin@mail2.sysu.edu.cn' && configData.password === '123456') {
        return [200, {
            code: 1, // 1代表注册/登录成功
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

// 注册mock
mock.onPost(path.register).reply((config) => {
    const configData = JSON.parse(config.data);
    if (configData.verifyCode === '123456') {
        return [200, {
            code: 1, // 1代表注册/登录成功
            token: 'admin',
            uid: 1,
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
            src: `https://loremflickr.com/${width}/${height}?${i}`,
            name: `社团${i}`,
            introduction: `社团${i}介绍`,
            memberNum: Math.floor(Math.random() * 1000) + 1000,
            clubID: i,
            type: '体育类',
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
            name: '张三',
            gender: '男',
            age: 20,
            studentID: '2019001001',
            faculty: '信息科学学院',
            email: 'admin@mail2.sysu.edu.cn',
            phone: '13800138000',
            hobby: '篮球',
            specialty: '计算机科学与技术',
            clubs: [
                {
                    clubID: 1,
                    name: '社团1',
                    position: '社长',
                    state: 0,
                    facultyIdea: 2,
                    youthLeagueCommitteeIdea: 0,
                    facultyIdeaContent: '社团理念不行',
                    youthLeagueCommitteeIdeaContent: '',
                },
                {
                    clubID: 2,
                    name: '社团2',
                    position: '副社长',
                    state: 1,
                    facultyIdea: 1,
                    youthLeagueCommitteeIdea: 1,
                    facultyIdeaContent: '审核通过',
                    youthLeagueCommitteeIdeaContent: '审核通过',
                },
                {
                    clubID: 3,
                    name: '社团3',
                    position: '干部',
                    state: 1,
                    facultyIdea: 1,
                    youthLeagueCommitteeIdea: 1,
                    facultyIdeaContent: '审核通过',
                    youthLeagueCommitteeIdeaContent: '审核通过',
                },
                {
                    clubID: 4,
                    name: '社团4',
                    position: '普通成员',
                    state: 1,
                    facultyIdea: 1,
                    youthLeagueCommitteeIdea: 1,
                    facultyIdeaContent: '审核通过',
                    youthLeagueCommitteeIdeaContent: '审核通过',
                },
                {
                    clubID: 5,
                    name: '社团5',
                    position: '社长',
                    state: 0,
                    facultyIdea: 0,
                    youthLeagueCommitteeIdea: 0,
                    facultyIdeaContent: '等待学院审核',
                    youthLeagueCommitteeIdeaContent: '',
                },
                {
                    clubID: 6,
                    name: '社团6',
                    position: '社长',
                    state: 0,
                    facultyIdea: 1,
                    youthLeagueCommitteeIdea: 2,
                    facultyIdeaContent: '审核通过',
                    youthLeagueCommitteeIdeaContent: '已经有类似的社团了',
                },
            ],
            achievements: [
                {
                    name: '活动xxx', // 获得荣誉的活动名称
                    award: 'xxx活动xxx名', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxxxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxxxxxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxxxxxxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-03 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxxxxxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
                    awardWiningTime: '2024-05-04 00:00:00', // 获奖时间
                },
                {
                    name: '项目xxxxxxxxxxxxxx',
                    award: 'xxxx项目xxx名誉', // 荣誉名称
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
            activityID: i,
            name: '社团' + config.params.clubID + '的活动' + i,
            introduction: '活动' + i + '介绍',
            startTime: '2024-04-29 00:00:00',
            endTime: '2024-05-03 00:00:00',
            location: '活动' + i + '地点',
            image: `https://loremflickr.com/400/300?${i}`,
        })
        notices.push({
            noticeID: i,
            title: '社团' + config.params.clubID + '的通知' + i,
            content: '通知' + i + '介绍',
            image: `https://loremflickr.com/400/300?${i}`,
            releaseTime: '2024-04-29 00:00:00',
        })
    }
    return [200, {
        code: 6, // 6代表获取社团活动通知
        clubID: config.params.clubID,
        activities: activities,
        notices: notices
    }]
})

// 获取社团简介mock
mock.onGet(path.clubIntroduction).reply((config) => {
    return [200, {
        code: 7, // 7代表获取社团简介
        introduction: [
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
                name: '张三',
                position: '社长',
                phone: '12345678901',
                image: 'https://loremflickr.com/300/300',
            },
            executives: [
                {
                    name: '李四',
                    position: '副社长',
                    image: 'https://loremflickr.com/300/300',
                },
                {
                    name: '王五',
                    position: '干部',
                    image: 'https://loremflickr.com/300/300',
                },
                {
                    name: '赵六',
                    position: '干部',
                    image: 'https://loremflickr.com/300/300',
                }
            ],
            others: {
                number: 100,
            }
        }
    }]
})