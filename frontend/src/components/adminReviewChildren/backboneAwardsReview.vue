<style scoped>
.clubEvaluationContainer {
  width: 100%;
  height: 690px;
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
</style>

<template>
  <t-aside>
    <t-list stripe="true" style="max-height: 680px; width: 250px" :scroll="{ type: 'virtual' }">
      <t-list-item v-for="evaluation in backBoneEvaluations" style="width: auto" :key="evaluation.recordId">
        <t-list-item-meta :title = "evaluation.stName" :description="evaluation.clubName"/>
        <template #action>
          <t-button
              shape="round"
              :theme="theme[choose === evaluation.recordId ? 1 : 0]"
              @click="detail(evaluation)"
          >
            <template #icon>
              <ArrowRightIcon/>
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
          <t-row id="tableBorder">
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
              <t-col id="table" :span="10">{{backBoneEvaluate.tenurePeriod}}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="2">绩点</t-col>
              <t-col id="table" :span="2">{{backBoneEvaluate.achievements.gpa}}</t-col>
              <t-col id="table" :span="2">排名</t-col>
              <t-col id="table" :span="2">{{backBoneEvaluate.achievements.rank}}</t-col>
              <t-col id="table" :span="2">名次百分比</t-col>
              <t-col id="table" :span="2">{{backBoneEvaluate.achievements.rankRatio}}</t-col>
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
                      <t-col :span="4">{{item.time}}</t-col>
                      <t-col id="table" :span="4">{{item.location}}</t-col>
                      <t-col id="table" :span="4">{{item.organization}}</t-col>
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
                      <t-col :span="4">{{item.name}}</t-col>
                      <t-col id="table" :span="4">{{item.time}}</t-col>
                      <t-col id="table" :span="4">{{item.organization}}</t-col>
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
                      <t-col :span="4">{{item.name}}</t-col>
                      <t-col id="table" :span="4">{{item.time}}</t-col>
                      <t-col id="table" :span="4">{{item.organization}}</t-col>
                    </t-row>
                  </t-popup>
                </div>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="2">学生社团工作情况</t-col>
              <t-col id="table" :span="10">
                <t-text>{{backBoneEvaluate.clubWorkStatus}}</t-text>
              </t-col>
            </t-row>
          </t-row>
        </div>
      </div>
    </t-content>
    <t-footer>
      <t-space direction="horizontal" size="50%" style="margin-left: 15%">
        <t-button theme="danger">评优条件不符</t-button>
        <t-button theme="success">成为优秀骨干</t-button>
      </t-space>
    </t-footer>
  </t-layout>
</template>

<script setup>
import {ref} from "vue";
import {ArrowDownIcon, ArrowRightIcon} from "tdesign-icons-vue-next";

const choose = ref(-1)
const theme = ["primary", "success"]
const icon = [ArrowDownIcon.stem, ArrowRightIcon.stem]
const backBoneEvaluate = ref({
  recordId:0,
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
const detail = (data) =>{
  console.log(data)
  choose.value = data.recordId
  backBoneEvaluate.value = data
}

const backBoneEvaluations = getEvaluations()
function getEvaluations(){
  const data = [];
  for(let i = 0; i < 10; i++){
    data.push({
      recordId:i,
      stName: ["小红","小强","小王"][i % 3],
      studentNumber: [1,2,3][i % 3],
      contact: '114514',
      college: ["人工智能","软件工程","测绘"][i % 3],
      politicalStatus: ["群众","党员"][i % 2],
      clubName: ["篮球社","围棋社","街舞社"][i % 3],
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
  return data
}

</script>
