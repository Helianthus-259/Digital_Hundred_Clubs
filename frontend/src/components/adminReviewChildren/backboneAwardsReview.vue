<style scoped>
.clubEvaluationContainer {
  width: 100%;
  height: 550px;
  background: #ffffff;
  border-radius: 10px;
  overflow-y: auto;
}

/*滚动条样式*/
.clubEvaluationContainer::-webkit-scrollbar {
  width: 8px;
  /* 滚动条宽度 */
}

.clubEvaluationContainer::-webkit-scrollbar-track {
  background-color: #f1f1f1;
  /* 滚动条轨道背景色 */
}

.clubEvaluationContainer::-webkit-scrollbar-thumb {
  background-color: #888;
  /* 滚动条滑块颜色 */
  border-radius: 4px;
  /* 滚动条滑块圆角 */
}

.clubEvaluationContainer::-webkit-scrollbar-thumb:hover {
  background-color: #555;
  /* 鼠标hover时滑块颜色 */
}

.titleContainer {
  height: 70px;
  font-size: 24px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tableContainer {
  width: 80%;
  margin: 0 auto;
}

.t-row {
  width: 100%;
  align-items: center;
}

.t-row#border {
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
  <t-aside>
    <t-list stripe="true" style="max-height: 600px; width: 250px" :scroll="{ type: 'virtual' }">
      <t-list-item v-for="evaluation in backBoneEvaluations" style="width: auto" :key="evaluation.recordId">
        <t-list-item-meta :title="evaluation.stName" :description="evaluation.clubName + evaluation.declarationYear" />
        <template #action>
          <t-button shape="round" :theme="theme[choose === evaluation.recordId ? 1 : 0]" @click="detail(evaluation)">
            <template #icon>
              <ArrowRightIcon />
            </template>
          </t-button>
        </template>
      </t-list-item>
    </t-list>
  </t-aside>
  <t-layout>
    <t-content>
      <div class="clubEvaluationContainer">
        <div class="titleContainer">优秀学生社团骨干申请表</div>
        <div class="tableContainer">
          <t-row id="border">
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
              <t-col id="table" :span="10">{{ backBoneEvaluate.tenurePeriod }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="2">绩点</t-col>
              <t-col id="table" :span="2">{{ backBoneEvaluate.achievements.gpa }}</t-col>
              <t-col id="table" :span="2">排名</t-col>
              <t-col id="table" :span="2">{{ backBoneEvaluate.achievements.rank }}</t-col>
              <t-col id="table" :span="2">名次百分比</t-col>
              <t-col id="table" :span="2">{{ backBoneEvaluate.achievements.rankRatio }}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="2">参与学校和指导单位组织培训情况</t-col>
              <t-col id="table" style="display: inline;" :span="10">
                <t-row>
                  <t-col :span="4">时间</t-col>
                  <t-col id="table" :span="4">地点</t-col>
                  <t-col id="table" :span="4">组织单位</t-col>
                </t-row>
                <div v-for="(item) in backBoneEvaluate.trainingParticipation">
                  <t-popup placement="left-bottom">
                    <t-row id="table">
                      <t-col :span="4">{{ item.time }}</t-col>
                      <t-col id="table" :span="4">{{ item.location }}</t-col>
                      <t-col id="table" :span="4">{{ item.organization }}</t-col>
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
                <div v-for="(item) in backBoneEvaluate.associationAwards">
                  <t-popup placement="left-bottom">
                    <t-row id="table">
                      <t-col :span="4">{{ item.name }}</t-col>
                      <t-col id="table" :span="4">{{ item.time }}</t-col>
                      <t-col id="table" :span="4">{{ item.organization }}</t-col>
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
                <div v-for="(item) in backBoneEvaluate.awards">
                  <t-popup placement="left-bottom">
                    <t-row id="table">
                      <t-col :span="4">{{ item.name }}</t-col>
                      <t-col id="table" :span="4">{{ item.time }}</t-col>
                      <t-col id="table" :span="4">{{ item.organization }}</t-col>
                    </t-row>
                  </t-popup>
                </div>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="2">学生社团工作情况</t-col>
              <t-col id="table" :span="10">
                <t-text>{{ backBoneEvaluate.clubWorkStatus }}</t-text>
              </t-col>
            </t-row>
          </t-row>
        </div>
      </div>
      <t-space direction="horizontal" size="50%" style="margin-left: 30%; height: 50px">
        <t-button theme="danger" style="margin-top: 10px" @click="unPassBackboneAwardsReview">评优条件不符</t-button>
        <t-button theme="success" style="margin-top: 10px" @click="passBackboneAwardsReview">成为优秀骨干</t-button>
      </t-space>
    </t-content>
  </t-layout>
</template>

<script setup>
import {onUnmounted, ref} from "vue";
import { ArrowDownIcon, ArrowRightIcon } from "tdesign-icons-vue-next";
import eventEmitter from "@/utils/eventEmitter.js";
import {APIEnum, APIEventEnum} from "@/Enum/index.js";
import {NotifyPlugin} from "tdesign-vue-next";

const choose = ref(-1)
const theme = ["primary", "success"]
const icon = [ArrowDownIcon.stem, ArrowRightIcon.stem]
const backBoneEvaluate = ref({
  recordId: null,
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
const detail = (data) => {
  console.log(data)
  choose.value = data.recordId
  backBoneEvaluate.value = data
  backBoneEvaluate.value.position = JSON.parse(localStorage.getItem('enumList')).positions[data.position].name
}

const backBoneEvaluations = ref([])

eventEmitter.emit(APIEventEnum.request, APIEnum.getBackBoneEvaluations)
eventEmitter.on(APIEventEnum.getBackBoneEvaluationsSuccess, 'getBackBoneEvaluationsSuccess', (data)=>{
  backBoneEvaluations.value = data.filter(backBoneEvaluation=>{return backBoneEvaluation.status === null})
})

const passBackboneAwardsReview = () => {
  if(backBoneEvaluate.value.recordId === null){
    NotifyPlugin.warning({
      title: '操作失败',
      content: '请选择任一骨干评优信息！',
    })
    return
  }
  eventEmitter.emit(APIEventEnum.request, APIEnum.passBackboneAwardsReview, {recordId: backBoneEvaluate.value.recordId})
}

const unPassBackboneAwardsReview = () => {
  if(backBoneEvaluate.value.recordId === null){
    NotifyPlugin.warning({
      title: '操作失败',
      content: '请选择任一骨干评优信息！',
    })
    return
  }
  eventEmitter.emit(APIEventEnum.request, APIEnum.unPassBackboneAwardsReview, {recordId: backBoneEvaluate.value.recordId})
}

eventEmitter.on(APIEventEnum.passBackboneAwardsReviewSuccess, 'passBackboneAwardsReviewSuccess', ()=>{
  NotifyPlugin.success({
    title: '操作成功',
    content: '通过骨干申请成功',
  })
})

eventEmitter.on(APIEventEnum.unPassBackboneAwardsReviewSuccess, 'unPassBackboneAwardsReviewSuccess', ()=>{
  NotifyPlugin.info({
    title: '操作成功',
    content: '驳回骨干申请成功',
  })
})


onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getBackBoneEvaluationsSuccess, 'getBackBoneEvaluationsSuccess')
  eventEmitter.off(APIEventEnum.passBackboneAwardsReviewSuccess, 'passBackboneAwardsReviewSuccess')
  eventEmitter.off(APIEventEnum.unPassBackboneAwardsReviewSuccess, 'unPassBackboneAwardsReviewSuccess')
})

</script>
