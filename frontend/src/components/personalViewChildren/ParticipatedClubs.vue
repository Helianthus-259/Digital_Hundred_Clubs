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

/* 滚动条样式 */
.contentViewBox::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.contentViewBox::-webkit-scrollbar-track {
    background-color: #f1f1f1;
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
                        <div v-show="manageButtonShow"
                            style="position: absolute; right: 190px; top: 130px; z-index: 99;">
                            <t-button theme="primary" size="small" @click="go2ClubManage">进入管理界面</t-button>
                        </div>
                        <ActivitiesView v-if="activitiesShow" :activities="activities" @click="clickHandle">
                        </ActivitiesView>
                        <NoticesView v-else :notices="notices"></NoticesView>
                    </div>
                    <div class="tabBarBox">
                        <t-head-menu v-model="value" @change="changeView">
                            <template #logo>
                                <img height="28" src="https://tdesign.gtimg.com/site/baseLogo-light.png" alt="logo" />
                            </template>
                            <t-menu-item v-for="item in list" :key="item.value" :value="item.value">
                                {{ item.label }}
                                <template #icon>
                                    <t-icon :name="item.icon" />
                                </template>
                            </t-menu-item>
                        </t-head-menu>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import store from '@/store';
import myCell from '../myCell.vue';
import { ref } from 'vue';
import { Icon as TIcon } from 'tdesign-icons-vue-next';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import NoticesView from '../participatedClubsChildren/NoticesView.vue';
import ActivitiesView from '../participatedClubsChildren/ActivitiesView.vue';
import mySteps from '../mySteps.vue';

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
    if (club.clubStatus === 1 && club.position !== "普通成员") {
        return true
    }
    return false
}
clubsManage.value = clubsJoin.value.filter(club => manage(club)) // 我管理的社团

const notManage = (club) => {
    if (club.clubStatus === 1 && club.position === "普通成员") {
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

eventEmitter.on(APIEventEnum.getClubActAndNtcSuccess, (data) => {
    activities.value = data.activities
    notices.value = data.notices
})

// 活动和通知的标签栏
const value = ref('activity');
const list = ref([
    { value: 'activity', label: '活动', icon: 'activity' },
    { value: 'notification', label: '通知', icon: 'notification' },
]);

const activitiesShow = ref(true)
// 更换展示的页面
const changeView = (value) => {
    if (value === 'activity') {
        activitiesShow.value = true
    } else {
        activitiesShow.value = false
    }
}

// 点击活动跳转到活动页面
const clickHandle = (activity) => {
    const parentRoute = store.state.parentRoute.activity
    const selfRoute = parentRoute + `${activity.activityId}/`
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setActivityId, activity.activityId)
}

const go2ClubManage = () => {
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setParentRoute, { owner: 'clubManage', value: store.state.clubId })
    eventEmitter.emit(RouterEventEnum.push, `/clubManage/${store.state.clubId}/`)
}

</script>