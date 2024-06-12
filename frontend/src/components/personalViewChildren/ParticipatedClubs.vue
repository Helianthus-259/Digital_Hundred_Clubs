<style scoped>
.mainBox {
    display: flex;
    width: 100%;
    height: 600px;
}

/* 左边栏 */
.leftSide {
    width: 20%;
    padding: 5px;
}

/* 左边部分内容 */
.leftSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    overflow-y: auto;
}


/*滚动条样式*/
.leftSideContent::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.leftSideContent::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.leftSideContent::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.leftSideContent::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.t-collapse-panel :deep(.t-collapse-panel__wrapper .t-collapse-panel__content) {
    padding: 0;
}

/* 右边栏 */
.rightSide {
    width: 80%;
    padding: 5px;
}

/* 右边部分内容 */
.rightSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;

}

/* 步骤条 */
.stepBarBox {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.contentViewBox {
    height: 90%;
    overflow-y: auto;
}

.t-card {
    width: 70%;
    margin: 0 auto;
    margin-top: 50px;
    background-color: #f1f1f1;
    position: relative;
}

.t-card :deep(.t-card__cover) {
    height: 300px;
    overflow: hidden;
    display: flex;
    align-items: center;
}

.t-card__cover img {
    width: 100%;
    height: auto;
    object-fit: cover;
    object-position: center;
}

.t-card__cover img:hover {
    cursor: pointer;
}

/* 滚动条样式 */
.contentViewBox::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.contentViewBox::-webkit-scrollbar-track {
    background-color: #ffffff;
    /* 滚动条轨道背景色 */
}

.contentViewBox::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.contentViewBox::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.tabBarBox {
    height: 10%;
}

.t-row {
    width: 100%;
    align-items: center;
}

.t-row#tableContainer {
    border: 2px solid #000;
}

.t-row#table {
    border-top: 2px solid #000;
}

.t-col {
    min-height: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.t-col#table {
    border-left: 2px solid #000;
}
</style>
<template>
    <div class="mainBox">
        <!-- 展示和我有关社团 -->
        <div class="leftSide">
            <div class="leftSideContent">
                <t-collapse>
                    <t-collapse-panel :value="0" header="我管理的">
                        <myCell :items="clubsManage" :panel-index="0" :current-panel-index="currentPanelIndex"
                            @click="getClubInfos">
                        </myCell>
                    </t-collapse-panel>
                    <t-collapse-panel :value="1" header="我加入的">
                        <myCell :items="clubsNotManage" :panel-index="1" :current-panel-index="currentPanelIndex"
                            @click="getClubInfos">
                        </myCell>
                    </t-collapse-panel>
                    <t-collapse-panel :value="2" header="审核中">
                        <myCell :items="clubsApply" :panel-index="2" :current-panel-index="currentPanelIndex"
                            @click="getClubInfos">
                        </myCell>
                    </t-collapse-panel>
                    <t-collapse-panel :value="3" header="申请失败">
                        <myCell :items="clubsFail" :panel-index="3" :current-panel-index="currentPanelIndex"
                            @click="getClubInfos">
                        </myCell>
                    </t-collapse-panel>
                </t-collapse>
            </div>
        </div>

        <!-- 展示社团的活动和通知/申请进度 -->
        <div class="rightSide">
            <div class="rightSideContent">
                <!-- 审核没完成显示 -->
                <div class="stepBarBox" v-if="weatherPass === 2">
                    <mySteps :count="count" :current="currentStep" :current-status="currentStatus" :steps="steps">
                    </mySteps>
                </div>
                <!--  审核通过了显示 -->
                <div style="width: 100%; height: 100%;" v-else-if="weatherPass === 1">
                    <div class="contentViewBox">
                        <t-card v-if="activitiesShow" v-for="(item, index) in activities">
                            <template #header>
                                {{ item.activityName }}
                            </template>
                            <template #cover>
                                <img :src="item.imageUrl" @click="go2ActivityDetail(item.activityId)">
                            </template>
                            <template #footer>
                                <div style="display: flex; justify-content: end;">
                                    <t-button variant="text" shape="square" :style="{ 'margin-right': '8px' }">
                                        <t-popup placement="top" show-arrow>
                                            <template #content>
                                                活动时间:{{ item.activityStartTime }} 至 {{ item.activityEndTime }}
                                            </template>
                                            <t-icon name="time" />
                                        </t-popup>
                                    </t-button>
                                    <t-button variant="text" shape="square" :style="{ 'margin-right': '8px' }">
                                        <t-popup :content="item.activityLocation" placement="top" show-arrow>
                                            <t-icon name="location" />
                                        </t-popup>
                                    </t-button>
                                    <t-button variant="text" shape="square" @click="go2ActivityDetail(item.activityId)">
                                        <t-popup placement="top" show-arrow>
                                            <template #content>
                                                <div v-html="item.activityIntroduction"></div>
                                            </template>
                                            <t-icon name="more" />
                                        </t-popup>
                                    </t-button>
                                </div>
                            </template>
                        </t-card>
                        <t-card v-else v-for="(item, index) in notices">
                            <template #header>
                                {{ item.title }}
                            </template>
                            <template #content>
                                {{ item.content }}
                            </template>
                        </t-card>
                    </div>
                    <div class="tabBarBox">
                        <t-head-menu v-model="value" @change="changeView">
                            <template #logo>
                                <img height="100%" src="/src/assets/sselogo20220915_0.png" alt="logo" />
                            </template>
                            <t-menu-item value="activity" content="活动">
                                <template #icon>
                                    <t-icon name="activity" />
                                </template>
                            </t-menu-item>
                            <t-menu-item value="notification" content="通知">
                                <template #icon>
                                    <t-icon name="notification" />
                                </template>
                            </t-menu-item>
                            <template #operations>
                                <t-button :disabled="!manageButtonShow" @click="openDialog()" variant="outline"
                                    shape="square">
                                    <t-popup :content="!manageButtonShow ? '无权限' : '骨干评优'" placement="top" show-arrow>
                                        <t-icon name="bone" />
                                    </t-popup>
                                </t-button>
                                &nbsp;&nbsp;
                                <t-button :disabled="!manageButtonShow" @click="go2ClubManage" variant="outline"
                                    shape="square">
                                    <t-popup :content="!manageButtonShow ? '无管理权限' : '进入管理界面'" placement="top"
                                        show-arrow>
                                        <t-icon name="work" />
                                    </t-popup>
                                </t-button>
                            </template>
                        </t-head-menu>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <myDialog ref="dialogRef">
        <template #header>
            <div style="font-size: 24px; font-weight: bold;">优秀学生社团骨干申请表</div>
        </template>
        <t-row id="tableContainer">
            <t-row>
                <t-col :span="2">姓名</t-col>
                <t-col id="table" :span="3">{{ backBoneEvaluate.stName }}</t-col>
                <t-col id="table" :span="1">学号</t-col>
                <t-col id="table" :span="2">{{ backBoneEvaluate.studentNumber }}</t-col>
                <t-col id="table" :span="1">手机号</t-col>
                <t-col id="table" :span="3">{{ backBoneEvaluate.contact }}</t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">院系</t-col>
                <t-col id="table" :span="4">{{ backBoneEvaluate.college }}</t-col>
                <t-col id="table" :span="2">所在社团</t-col>
                <t-col id="table" :span="4">{{ backBoneEvaluate.clubName }}</t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">政治面貌</t-col>
                <t-col id="table" :span="4">{{ backBoneEvaluate.politicalStatus }}</t-col>
                <t-col id="table" :span="2">担任职务</t-col>
                <t-col id="table" :span="4">{{ backBoneEvaluate.position }}</t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">任职时间</t-col>
                <t-col id="table" :span="10">
                    <t-date-range-picker clearable @change="tenurePeriodChange" />
                </t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">绩点</t-col>
                <t-col id="table" :span="2">
                    <t-input style="width: 60%;" borderless v-model="backBoneEvaluate.achievements.gpa"
                        placeholder=""></t-input>
                </t-col>
                <t-col id="table" :span="2">排名</t-col>
                <t-col id="table" :span="2">
                    <t-input style="width: 60%;" borderless v-model="backBoneEvaluate.achievements.rank"
                        placeholder=""></t-input>
                </t-col>
                <t-col id="table" :span="2">名次百分比</t-col>
                <t-col id="table" :span="2">
                    <t-input style="width: 60%;" v-model="backBoneEvaluate.achievements.rankRatio" borderless
                        placeholder="" suffix="%"></t-input>
                </t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">参与学校和指导单位组织培训情况</t-col>
                <t-col id="table" style="display: inline;" :span="10">
                    <t-row>
                        <t-col :span="4">时间</t-col>
                        <t-col id="table" :span="4">地点</t-col>
                        <t-col id="table" :span="4">组织单位</t-col>
                    </t-row>
                    <div v-for="(item, index) in backBoneEvaluate.trainingParticipation">
                        <t-popup placement="left-bottom">
                            <template #content>
                                <t-button shape="square" variant="text" @click="addTrainingParticipation">
                                    <t-icon name="add" />
                                </t-button>
                                <t-button shape="square" variant="text" @click="deleteTrainingParticipation(index)">
                                    <t-icon name="delete" />
                                </t-button>
                            </template>
                            <t-row id="table">
                                <t-col :span="4">
                                    <t-input style="width: 90%;" v-model="item.time" borderless
                                        placeholder=""></t-input>
                                </t-col>
                                <t-col id="table" :span="4">
                                    <t-input style="width: 90%;" v-model="item.location" borderless
                                        placeholder=""></t-input>
                                </t-col>
                                <t-col id="table" :span="4">
                                    <t-input style="width: 90%;" v-model="item.organization" borderless
                                        placeholder=""></t-input>
                                </t-col>
                            </t-row>
                        </t-popup>
                    </div>
                </t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">所在社团获奖情况</t-col>
                <t-col id="table" style="display: inline;" :span="10">
                    <t-row>
                        <t-col :span="4">名称</t-col>
                        <t-col id="table" :span="4">时间</t-col>
                        <t-col id="table" :span="4">颁发单位</t-col>
                    </t-row>
                    <div v-for="(item, index) in backBoneEvaluate.associationAwards">
                        <t-popup placement="left-bottom">
                            <template #content>
                                <t-button shape="square" variant="text" @click="addAssociationAwards">
                                    <t-icon name="add" />
                                </t-button>
                                <t-button shape="square" variant="text" @click="deleteAssociationAwards(index)">
                                    <t-icon name="delete" />
                                </t-button>
                            </template>
                            <t-row id="table">
                                <t-col :span="4">
                                    <t-input style="width: 90%;" v-model="item.name" borderless
                                        placeholder=""></t-input>
                                </t-col>
                                <t-col id="table" :span="4">
                                    <t-input style="width: 90%;" v-model="item.time" borderless
                                        placeholder=""></t-input>
                                </t-col>
                                <t-col id="table" :span="4">
                                    <t-input style="width: 90%;" v-model="item.organization" borderless
                                        placeholder=""></t-input>
                                </t-col>
                            </t-row>
                        </t-popup>
                    </div>
                </t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">以社团骨干身份获奖励情况</t-col>
                <t-col id="table" style="display: inline;" :span="10">
                    <t-row>
                        <t-col :span="4">名称</t-col>
                        <t-col id="table" :span="4">时间</t-col>
                        <t-col id="table" :span="4">颁发单位</t-col>
                    </t-row>
                    <div v-for="(item, index) in backBoneEvaluate.awards">
                        <t-popup placement="left-bottom">
                            <template #content>
                                <t-button shape="square" variant="text" @click="addAwards">
                                    <t-icon name="add" />
                                </t-button>
                                <t-button shape="square" variant="text" @click="deleteAwards(index)">
                                    <t-icon name="delete" />
                                </t-button>
                            </template>
                            <t-row id="table">
                                <t-col :span="4">
                                    <t-input style="width: 90%;" v-model="item.name" borderless
                                        placeholder=""></t-input>
                                </t-col>
                                <t-col id="table" :span="4">
                                    <t-input style="width: 90%;" v-model="item.time" borderless
                                        placeholder=""></t-input>
                                </t-col>
                                <t-col id="table" :span="4">
                                    <t-input style="width: 90%;" v-model="item.organization" borderless
                                        placeholder=""></t-input>
                                </t-col>
                            </t-row>
                        </t-popup>
                    </div>
                </t-col>
            </t-row>
            <t-row id="table">
                <t-col :span="2">学生社团工作情况</t-col>
                <t-col id="table" :span="10">
                    <t-textarea v-model="backBoneEvaluate.clubWorkStatus" placeholder="请输入内容"
                        :autosize="{ minRows: 10, maxRows: 10 }" />
                </t-col>
            </t-row>
        </t-row>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline"
                @click="submitBackBoneEvaluate">提交</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import store from '@/store';
import myCell from '../myCell.vue';
import { onUnmounted, reactive, ref } from 'vue';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import mySteps from '../mySteps.vue';
import formatDate from '@/utils'
import myDialog from '../myDialog.vue';
import { MessagePlugin } from 'tdesign-vue-next';

// 展示加入了的社团
const clubsJoin = ref(store.state.userInfo.clubs)

// 审核中的社团
const clubsApply = ref([])
// 申请失败社团
const clubsFail = ref([])
// 我管理的社团
const clubsManage = ref([])
// 不是我管理的社团
const clubsNotManage = ref([])

// positions
const positions = JSON.parse(localStorage.getItem('enumList')).positions

const applying = (club) => {
    if (club.clubStatus === 0 && club.collegeReviewStatus !== 0 && club.universityStudentUnionReviewStatus !== 0) {
        return true
    }
    return false
}
clubsApply.value = clubsJoin.value.filter(club => applying(club)) // 申请中的社团

const fail = (club) => {
    if (club.clubStatus === 0 && (club.collegeReviewStatus === 0 || club.universityStudentUnionReviewStatus === 0)) {
        return true
    }
    return false
}
clubsFail.value = clubsJoin.value.filter(club => fail(club)) // 申请失败的社团

const manage = (club) => {
    if (club.clubStatus === 1 && club.position !== 3) {
        return true
    }
    return false
}
clubsManage.value = clubsJoin.value.filter(club => manage(club)) // 我管理的社团

const notManage = (club) => {
    if (club.clubStatus === 1 && club.position === 3) {
        return true
    }
    return false
}
clubsNotManage.value = clubsJoin.value.filter(club => notManage(club)) // 不是我管理的社团

// 存储活动和通知
const activities = ref([])
const notices = ref([])

// 社团申请进度
const currentStep = ref(0)
const count = ref(4)
const currentStatus = ref('process')
const steps = ref([
    { content: '提交', idea: '提交成功' },
    { content: '学院审批', idea: '' },
    { content: '校团委审批', idea: '' },
    { content: '完成', idea: '' },
])

const weatherPass = ref(0)
const currentPanelIndex = ref(-1)
const manageButtonShow = ref(false)
// 点击社团获取社团相应的信息
const getClubInfos = (value, pd) => {
    steps.value[1].idea = ''
    steps.value[2].idea = ''
    currentPanelIndex.value = pd
    if (pd === 0) {
        manageButtonShow.value = true
    } else {
        manageButtonShow.value = false
    }
    if (value.clubStatus === 1) {
        if (value.clubId in store.state.clubsActAndNtc) {
            activities.value = store.state.clubsActAndNtc[value.clubId].activities
            notices.value = store.state.clubsActAndNtc[value.clubId].notices
        } else {
            eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActAndNtc, { clubId: value.clubId })
        }
        eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubId, value.clubId)
        weatherPass.value = 1
        steps.value[1].idea = value.collegeReviewOpinion
        steps.value[2].idea = value.universityStudentUnionReviewOpinion
    } else {
        weatherPass.value = 2
        if (value.collegeReviewStatus !== 1) {
            currentStep.value = 1
            if (value.collegeReviewStatus === null) {
                currentStatus.value = 'process'
                steps.value[1].idea = '等待学院审核'
            } else if (value.collegeReviewStatus === 0) {
                currentStatus.value = 'error'
                steps.value[1].idea = value.collegeReviewOpinion
            }
        } else {
            steps.value[1].idea = value.collegeReviewOpinion
            currentStep.value = 2
            if (value.universityStudentUnionReviewStatus === null) {
                currentStatus.value = 'process'
                steps.value[2].idea = '等待校团委审核'
            } else if (value.universityStudentUnionReviewStatus === 0) {
                currentStatus.value = 'error'
                steps.value[2].idea = value.universityStudentUnionReviewOpinion
            } else {
                currentStep.value = 3
                currentStatus.value = 'finish'
                steps.value[3].idea = '社团创建成功'
            }
        }
    }
}

eventEmitter.on(APIEventEnum.getClubActAndNtcSuccess, 'getClubActAndNtcSuccess', (data) => {
    activities.value = data.activities
    activities.value.map((item, index) => {
        item.activityStartTime = formatDate(new Date(item.activityStartTime), 'yyyy-MM-dd')
        item.activityEndTime = formatDate(new Date(item.activityEndTime), 'yyyy-MM-dd')
        if (item.activityIntroduction.length > 10) {

            item.activityIntroduction = item.activityIntroduction.substring(0, 10) + "...";
        }

    })
    notices.value = data.notices
})

eventEmitter.on(APIEventEnum.getClubActAndNtcFail, 'getClubActAndNtcFail', (data) => {
    notices.value = data.notices
})

// 活动和通知的标签栏
const value = ref('activity');

const activitiesShow = ref(true)
// 更换展示的页面
const changeView = (value) => {
    if (value === 'activity') {
        activitiesShow.value = true
    } else {
        activitiesShow.value = false
    }
}


const go2ClubManage = () => {
    console.log(store.state.parentRoute.clubManage + store.state.clubId);
    eventEmitter.emit(RouterEventEnum.push, store.state.parentRoute.clubManage + store.state.clubId, true)
}

// 点击活动跳转到活动页面
const go2ActivityDetail = (activityId) => {
    eventEmitter.emit(RouterEventEnum.push, store.state.parentRoute.activity + activityId, true)
}

// 骨干评优
const backBoneEvaluate = reactive({
    stName: '',
    studentNumber: '',
    contact: '',
    college: '',
    politicalStatus: '',
    clubName: '',
    position: '',
    tenurePeriod: '',
    achievements: {
        gpa: '',
        rank: '',
        rankRatio: '',
    },
    trainingParticipation: [
        { time: '', location: '', organization: '' },
    ],
    associationAwards: [
        { name: '', time: '', organization: '' },
    ],
    awards: [
        { name: '', time: '', organization: '' },
    ],
    clubWorkStatus: '',
})

const backBoneEvaluateValidate = () => {
    let flag = true
    if (backBoneEvaluate.stName === '') {
        flag = false
    }
    if (backBoneEvaluate.studentNumber === '') {
        flag = false
    }
    if (backBoneEvaluate.contact === '') {
        flag = false
    }
    if (backBoneEvaluate.college === '') {
        flag = false
    }
    if (backBoneEvaluate.politicalStatus === '') {
        flag = false
    }
    if (backBoneEvaluate.clubName === '') {
        flag = false
    }
    if (backBoneEvaluate.position === '') {
        flag = false
    }
    if (backBoneEvaluate.tenurePeriod === '') {
        flag = false
    }
    if (backBoneEvaluate.achievements.gpa === '') {
        flag = false
    }
    if (backBoneEvaluate.achievements.rank === '') {
        flag = false
    }
    if (backBoneEvaluate.achievements.rankRatio === '') {
        flag = false
    }
    if (backBoneEvaluate.trainingParticipation.length === 0) {
        flag = false
    }
    if (backBoneEvaluate.associationAwards.length === 0) {
        flag = false
    }
    if (backBoneEvaluate.awards.length === 0) {
        flag = false
    }
    if (backBoneEvaluate.clubWorkStatus === '') {
        flag = false
    }
    return flag
}

// 为部分值赋值
const setValue = () => {
    backBoneEvaluate.stName = store.state.userInfo.stName
    backBoneEvaluate.studentNumber = store.state.userInfo.studentNumber
    backBoneEvaluate.contact = store.state.userInfo.contact
    backBoneEvaluate.college = store.state.userInfo.college
    backBoneEvaluate.politicalStatus = store.state.userInfo.politicalStatus
    const clubNow = clubsManage.value.filter((item) => item.clubId === store.state.clubId)[0]
    backBoneEvaluate.clubName = clubNow.clubName
    backBoneEvaluate.position = clubNow.position
}

const clearBackBoneEvaluate = () => {
    backBoneEvaluate.stName = ''
    backBoneEvaluate.studentNumber = ''
    backBoneEvaluate.contact = ''
    backBoneEvaluate.college = ''
    backBoneEvaluate.politicalStatus = ''
    backBoneEvaluate.clubName = ''
    backBoneEvaluate.position = ''
    backBoneEvaluate.tenurePeriod = ''
    backBoneEvaluate.achievements.gpa = ''
    backBoneEvaluate.achievements.rank = ''
    backBoneEvaluate.achievements.rankRatio = ''
    backBoneEvaluate.trainingParticipation = [
        { time: '', location: '', organization: '' },
    ]
    backBoneEvaluate.associationAwards = [
        { name: '', time: '', organization: '' },
    ]
    backBoneEvaluate.awards = [
        { name: '', time: '', organization: '' },
    ]
    backBoneEvaluate.clubWorkStatus = ''
}

const tenurePeriodChange = (value) => {
    backBoneEvaluate.tenurePeriod = value[0] + ' 至 ' + value[1]
}

const addTrainingParticipation = () => {
    backBoneEvaluate.trainingParticipation.push({ time: '', location: '', organization: '' })
}

const addAssociationAwards = () => {
    backBoneEvaluate.associationAwards.push({ name: '', time: '', organization: '' })
}

const addAwards = () => {
    backBoneEvaluate.awards.push({ name: '', time: '', organization: '' })
}

const deleteTrainingParticipation = (index) => {
    if (backBoneEvaluate.trainingParticipation.length === 1) {
        return MessagePlugin.warning('至少保留一项')
    }
    backBoneEvaluate.trainingParticipation.splice(index, 1)
}

const deleteAssociationAwards = (index) => {
    if (backBoneEvaluate.associationAwards.length === 1) {
        return MessagePlugin.warning('至少保留一项')
    }
    backBoneEvaluate.associationAwards.splice(index, 1)
}

const deleteAwards = (index) => {
    if (backBoneEvaluate.awards.length === 1) {
        return MessagePlugin.warning('至少保留一项')
    }
    backBoneEvaluate.awards.splice(index, 1)
}

const submitBackBoneEvaluate = () => {
    if (!backBoneEvaluateValidate()) {
        return MessagePlugin.warning('请填写完整信息')
    }
    eventEmitter.emit(APIEventEnum.request, APIEnum.postBackBoneEvaluate, { ...backBoneEvaluate, clubId: store.state.clubId, studentId: store.state.studentId })
}

eventEmitter.on(APIEventEnum.postBackBoneEvaluateSuccess, 'postBackBoneEvaluateSuccess', () => {
    MessagePlugin.success('提交成功')
    closeDialog()
})

const dialogRef = ref(null)
const openDialog = () => {
    dialogRef.value.openDialog()
    setValue()
}

const closeDialog = () => {
    dialogRef.value.closeDialog()
    clearBackBoneEvaluate()
}


onUnmounted(() => {
    eventEmitter.off(APIEventEnum.postBackBoneEvaluateSuccess, 'postBackBoneEvaluateSuccess')
    eventEmitter.off(APIEventEnum.getClubActAndNtcSuccess, 'getClubActAndNtcSuccess')
    eventEmitter.off(APIEventEnum.getClubActAndNtcFail, 'getClubActAndNtcFail')
})
</script>