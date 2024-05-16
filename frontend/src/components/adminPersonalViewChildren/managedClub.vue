<style scoped>
.mainBox {
    display: flex;
    width: 100%;
    height: 600px;
}

/* 左边栏 */
.leftSide {
    width: 80%;
    padding: 5px;
}

/* 左边部分内容 */
.leftSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    overflow-y: auto;
    display: flex;
    justify-content: center;
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

.managedClubInfo {
    width: 60%;
}

/* 右边栏 */
.rightSide {
    width: 20%;
    padding: 5px;
}

/* 右边部分内容 */
.rightSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;

}
.ribbon {
    width: 100%;
    height: 100%;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    /* 按钮样式 */
    .t-button {
        margin: 5px;
        width: 65%;
        height: 40px;
        font-size: 16px;
        overflow: hidden;
    }
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
        <!-- 展示管理员的管理社团 -->
        <div class="leftSide">
            <div class="leftSideContent">
                <div class="managedClubInfo">
                    <t-cell-group bordered>
                        <t-cell title="社团id" :note="clubId" />
                        <t-cell title="社团名称" :note="clubName" />
                        <t-cell title="社团建立时间" :note="establishedTime" />
                        <t-cell title="社团附属单位id" :note="affiliatedUnitId" />
                        <!--<t-cell title="社团简介" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="100" v-model="clubIntroduction" placeholder="请输入社团简介（50字以内）" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>-->

                        <t-cell title="社团简介" hover style="height: 180px">
                            <template #note>
                                <t-textarea style="height: 140px; width:300px;" class="textarea-example" v-model="clubIntroduction"  placeholder="请输入社团简介（50字以内）" :readonly="readOnly" :bordered=true :indicator=true :maxcharacter=100></t-textarea>
                            </template>
                        </t-cell>

                        <t-cell title="社团类别" :note="clubSort" />
                        <t-cell title="社团状态" :note="clubStatus" />
                        <t-cell title="行政指导老师" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="11" v-model="administrativeAdvisorName" placeholder="请输入行政指导老师姓名" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>
                        <t-cell title="业务指导老师" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="11" v-model="businessAdvisorName" placeholder="请输入业务指导老师姓名" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>
                        <t-cell title="联系人ID" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="11" v-model="contactsId" placeholder="请输入联系人ID" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>
                        <t-cell title="社团主部所在校区" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="11" v-model="location" placeholder="请输入校区" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>
                        <t-cell title="社团主部所在校区" hover style="height: 56px">
                            <template #note>
                                <mySelect v-model="location" :options="campusOptions"></mySelect>
                            </template>
                        </t-cell>
                        <t-cell title="社团主部所在校区" hover style="height: 56px">
                            <template #note>
                                <mySelect v-model="location" :options="campusOptions" :disabled="readOnly"></mySelect>
                            </template>
                        </t-cell>
                        
                        <t-cell title="社团成员总人数" :note="totalMembership" />
                        <t-cell title="成员财务是否公开" :note="financePublicity" />
                    </t-cell-group>

                    <div style="display: flex; justify-content: center; padding: 10px">
                        <t-button v-show="!readOnly" @click="save">保存</t-button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 编辑按钮 -->
        <div class="rightSide">
            <div class="rightSideContent">
                <div class="ribbon">
                    <t-button theme="primary" @click="readOnly = !readOnly">编辑信息</t-button>
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
import mySelect from '../mySelect.vue';

// 展示管理的社团信息
const clubId = ref('')
const clubName = ref('')
const establishedTime = ref('')
const affiliatedUnitId = ref('')
const clubIntroduction = ref('')
const clubSort = ref('')
const clubStatus = ref('')
const administrativeAdvisorName = ref('')
const businessAdvisorName = ref('')
const contactsId = ref('')
const location = ref('')
const totalMembership = ref('')
const financePublicity = ref('')

const managedClubInfo = ref({})

const readOnly = ref(true)

// 校区
const campusOptions = ref([
    { label: '广州南校', value: '广州南校' },
    { label: '广州东校', value: '广州东校' },
    { label: '广州北校', value: '广州北校' },
    { label: '珠海校区', value: '珠海校区' },
    { label: '深圳校区', value: '深圳校区' },
])
// 为上面定义的变量赋值
function assignment() {
    clubId.value = managedClubInfo.value.clubId
    clubName.value = managedClubInfo.value.clubName
    establishedTime.value = managedClubInfo.value.establishedTime
    affiliatedUnitId.value = managedClubInfo.value.affiliatedUnitId
    clubIntroduction.value = managedClubInfo.value.clubIntroduction
    clubSort.value = managedClubInfo.value.clubSort
    clubStatus.value = managedClubInfo.value.clubStatus
    administrativeAdvisorName.value = managedClubInfo.value.administrativeAdvisorName
    businessAdvisorName.value = managedClubInfo.value.businessAdvisorName
    contactsId.value = managedClubInfo.value.contactsId
    location.value = managedClubInfo.value.location
    totalMembership.value = managedClubInfo.value.totalMembership
    financePublicity.value = managedClubInfo.value.financePublicity
}

// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 保存修改后的信息
function save() {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminInfo, {
        adminId: store.state.adminId,
        clubIntroduction: clubIntroduction.value,
    })
    managedClubInfo.value.clubIntroduction = clubIntroduction.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setAdminInfo, managedClubInfo.value)
    readOnly.value = !readOnly.value
}

if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
} else {
    managedClubInfo.value = store.state.userInfo.clubs
    assignment()
}

eventEmitter.on(APIEventEnum.getAdminInfoSuccess, (data) => {
    managedClubInfo.value = data.clubs
    assignment()
})

</script>