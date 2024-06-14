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

.text {
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}
</style>

<template>
  <t-aside>
    <t-list stripe="true" style="max-height: 600px; width: 250px" :scroll="{ type: 'virtual' }">
      <t-list-item v-for="evaluation in evaluations" style="width: auto" :key="evaluation.recordId">
        <t-list-item-meta :title="evaluation.clubName" :description="evaluation.declarationYear" />
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
        <div class="titleContainer">优秀学生社团申请表</div>
        <div class="tableContainer">
          <t-row id="border">
            <t-row>
              <t-col :span="3">
                <div class="text">学生社团名称</div>
              </t-col>
              <t-col :span="9" id="table">
                {{ clubEvaluationInfo.clubName }}
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">学生社团总人数</div>
              </t-col>
              <t-col :span="3" id="table">
                {{ clubEvaluationInfo.totalMembers }}
              </t-col>
              <t-col :span="3" id="table">
                <div class="text">学生社团骨干人数</div>
              </t-col>
              <t-col :span="3" id="table">
                {{ clubEvaluationInfo.backboneNumber }}
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="9">
                <div class="text">学生骨干是中共党员、入党积极分子或提交入党申请书人数</div>
              </t-col>
              <t-col :span="3" id="table">
                {{ clubEvaluationInfo.communistRelatedBackBoneNumber }}
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">行政指导老师</div>
              </t-col>
              <t-col :span="3" id="table">
                {{ clubEvaluationInfo.administrativeGuideTeacherName }}
              </t-col>
              <t-col :span="3" id="table">
                <div class="text">业务指导老师</div>
              </t-col>
              <t-col :span="3" id="table">
                {{ clubEvaluationInfo.businessGuideTeacherName }}
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">换届情况</div>
              </t-col>
              <t-col style="display: inline;" :span="3" id="table">
                <t-row>
                  <t-col :span="12">
                    <div class="text">换届方式</div>
                  </t-col>
                </t-row>
                <t-row id="table">
                  <t-col :span="12">
                    <div class="text">参与人数</div>
                  </t-col>
                </t-row>
                <t-row id="table">
                  <t-col :span="12">
                    <div class="text">指导教师是否参与指导</div>
                  </t-col>
                </t-row>
              </t-col>
              <t-col style="display: inline;" :span="6" id="table">
                <t-row>
                  <t-col :span="12">
                    <t-radio-group variant="primary-filled" :value="clubEvaluationInfo.handoverMethod">
                      <t-radio-button value='0'>全员大会</t-radio-button>
                      <t-radio-button value='1'>其他</t-radio-button>
                    </t-radio-group>
                  </t-col>
                </t-row>
                <t-row id="table">
                  <t-col :span="12">{{ clubEvaluationInfo.handoverParticipantsCount }}</t-col>
                </t-row>
                <t-row id="table">
                  <t-col :span="12">
                    <t-radio-group :value="clubEvaluationInfo.guideTeacher">
                      <t-radio-button value='1'>是</t-radio-button>
                      <t-radio-button value='0'>否</t-radio-button>
                    </t-radio-group>
                  </t-col>
                </t-row>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">财务管理</div>
              </t-col>
              <t-col :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="8">
                    <div class="text">是否向社团成员进行财务公开</div>
                  </t-col>
                  <t-col :span="4" id="table">
                    {{ clubEvaluationInfo.isFinancialInformationPublic }}
                  </t-col>
                </t-row>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">全员大会、骨干例会情况</div>
              </t-col>
              <t-col style="display: inline;" :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="4">
                    <div class="text">时间</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">地点/线上平台</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">全员大会/骨干例会</div>
                  </t-col>
                </t-row>
                <div v-for="(item) in clubEvaluationInfo.meetings">
                  <t-popup placement="left-bottom">
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="4">
                        {{ item.meetingTime ? formatDate(new Date(item.meetingTime), 'yyyy-MM-dd hh:mm:ss') : '' }}
                      </t-col>
                      <t-col id="table" :span="4">
                        {{ item.location }}
                      </t-col>
                      <t-col id="table" :span="4">
                        <t-radio-group variant="primary-filled"
                          :value="item.category">
                          <t-radio-button :value=0>全员大会</t-radio-button>
                          <t-radio-button :value=1>骨干例会</t-radio-button>
                        </t-radio-group>
                      </t-col>
                    </t-row>
                  </t-popup>
                </div>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团获奖情况</div>
              </t-col>
              <t-col style="display: inline;" :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="4">
                    <div class="text">名称</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">时间</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">颁发单位</div>
                  </t-col>
                </t-row>
                <div v-for="(item) in clubEvaluationInfo.associationAwards">
                  <t-popup placement="left-bottom">
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="4">{{ item.awardName }}</t-col>
                      <t-col id="table" :span="4">{{ item.awardTime ? formatDate(new Date(item.awardTime), 'yyyy-MM-dd hh:mm:ss') : '' }}</t-col>
                      <t-col id="table" :span="4">{{ item.issuingAuthority }}</t-col>
                    </t-row>
                  </t-popup>
                </div>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">宣传管理成效</div>
              </t-col>
              <t-col style="display: inline;" :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="8">
                    <div class="text">向学校平台投稿次数</div>
                  </t-col>
                  <t-col id="table" :span="4">{{ clubEvaluationInfo.publicityManagementEffectiveness.submissionsCount
                    }}</t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="4">
                    <div class="text">获校级以上宣传情况</div>
                  </t-col>
                  <t-col style="display: inline;" :span="8">
                    <t-row>
                      <t-col id="table" :span="6">
                        <div class="text">宣传平台</div>
                      </t-col>
                      <t-col id="table" :span="6">
                        <div class="text">报道内容</div>
                      </t-col>
                    </t-row>
                    <div
                      v-for="(item) in clubEvaluationInfo.publicityManagementEffectiveness.PublicityAboveSchoolLevel">
                      <t-popup placement="left-bottom">
                        <t-row id="table">
                          <t-col id="table" :span="6">{{ item.platform }}</t-col>
                          <t-col id="table" :span="6">{{ item.content }}</t-col>
                        </t-row>
                      </t-popup>
                    </div>
                  </t-col>
                </t-row>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">学生社团承办校级以上活动主办方及活动名称</div>
              </t-col>
              <t-col style="display: inline;" :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="4">
                    <div class="text">类别</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">主办方</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">活动名称</div>
                  </t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="4">
                    <div class="text">校级</div>
                  </t-col>
                  <t-col style="display: inline;" :span="8">
                    <div v-for="(item, index) in clubEvaluationInfo.hostedSchoolLevelActivities.schoolLv">
                      <t-popup placement="left-bottom">
                        <t-row :id="index === 0 ? '' : 'table'">
                          <t-col id="table" :span="6">{{ item.host }}</t-col>
                          <t-col id="table" :span="6">{{ item.activityName }}</t-col>
                        </t-row>
                      </t-popup>
                    </div>
                  </t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="4">
                    <div class="text">市级</div>
                  </t-col>
                  <t-col style="display: inline;" :span="8">
                    <div v-for="(item, index) in clubEvaluationInfo.hostedSchoolLevelActivities.municipal">
                      <t-popup placement="left-bottom">
                        <t-row :id="index === 0 ? '' : 'table'">
                          <t-col id="table" :span="6">{{ item.host }}</t-col>
                          <t-col id="table" :span="6">{{ item.activityName }}</t-col>
                        </t-row>
                      </t-popup>
                    </div>
                  </t-col>
                </t-row>
                <t-row style="border-left: 2px solid #000;" id="table">
                  <t-col :span="4">
                    <div class="text">省级及以上</div>
                  </t-col>
                  <t-col style="display: inline;" :span="8">
                    <div v-for="(item, index) in clubEvaluationInfo.hostedSchoolLevelActivities.provincial">
                      <t-popup placement="left-bottom">
                        <t-row :id="index === 0 ? '' : 'table'">
                          <t-col id="table" :span="6">{{ item.host }}</t-col>
                          <t-col id="table" :span="6">{{ item.activityName }}</t-col>
                        </t-row>
                      </t-popup>
                    </div>
                  </t-col>
                </t-row>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">学生社团活动情况</div>
              </t-col>
              <t-col style="display: inline;" :span="9">
                <t-row style="border-left: 2px solid #000;">
                  <t-col :span="4">
                    <div class="text">活动名称</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">举办时间</div>
                  </t-col>
                  <t-col id="table" :span="4">
                    <div class="text">活动成效</div>
                  </t-col>
                </t-row>
                <div v-for="(item) in clubEvaluationInfo.activities">
                  <t-popup placement="left-bottom">
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="4">{{ item.activityName }}</t-col>
                      <t-col id="table" :span="4">{{ item.activityEndTime ? formatDate(new Date(item.activityEndTime), 'yyyy-MM-dd hh:mm:ss') : '' }}</t-col>
                      <t-col id="table" :span="4">{{ item.activityEffect }}</t-col>
                    </t-row>
                  </t-popup>
                </div>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团育人成效案例</div>
              </t-col>
              <t-col :span="9" id="table">
                <t-link theme="primary">{{ clubEvaluationInfo.clubEducationCaseAttachment }}</t-link>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团代表性照片</div>
              </t-col>
              <t-col :span="9" id="table">
                <t-link theme="primary">{{ clubEvaluationInfo.imageUrl }}</t-link>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团工作简介</div>
              </t-col>
              <t-col :span="9" id="table">
                <t-text>{{ clubEvaluationInfo.clubWorkIntroduction }}</t-text>
              </t-col>
            </t-row>
          </t-row>
        </div>
      </div>
      <t-space direction="horizontal" size="50%" style="margin-left: 30%; height: 50px">
        <t-button theme="danger" style="margin-top: 10px" @click="unPassClubAwardReview">驳回评优</t-button>
        <t-button theme="success" style="margin-top: 10px" @click="passClubAwardReview">通过评优</t-button>
      </t-space>
    </t-content>
  </t-layout>
</template>

<script setup>
import { onUnmounted, ref } from "vue";
import { ArrowDownIcon, ArrowRightIcon } from "tdesign-icons-vue-next";
import eventEmitter from "@/utils/eventEmitter.js";
import { APIEnum, APIEventEnum } from "@/Enum/index.js";
import { NotifyPlugin } from "tdesign-vue-next";
import formatDate from "@/utils";


const evaluations = ref([])
const choose = ref(-1)
const theme = ["primary", "success"]
const icon = [ArrowDownIcon.stem, ArrowRightIcon.stem]
const clubEvaluationInfo = ref({
  recordId: null,
  clubName: '',
  handoverMethod: '',
  handoverParticipantsCount: '',
  isFinancialInformationPublic: '',
  totalMembers: '',
  administrativeGuideTeacherName: '',
  businessGuideTeacherName: '',
  mainCompus: "",
  backboneNumber: '',
  communistRelatedBackBoneNumber: '',
  declarationYear: '',
  guideTeacher: '',
  meetings: [
    {
      activityId: '',
      clubId: '',
      time: '',
      location: '',
      staffMeetingOrbackBoneMeeting: '',//0全员大会,1骨干例会
      guideTeacher: '',
    }
  ],
  associationAwards: [
    {
      awardName: '',
      awardTime: '',
      issuingAuthority: ''
    }
  ],
  publicityManagementEffectiveness: {
    submissionsCount: '',
    PublicityAboveSchoolLevel: [
      {
        platform: '',
        content: '',
      }
    ],
  },
  hostedSchoolLevelActivities: {
    schoolLv: [
      {
        host: '',
        activityName: '',
      }
    ],
    municipal: [
      {
        host: '',
        activityName: '',
      }
    ],
    provincial: [
      {
        host: '',
        activityName: '',
      }
    ]
  },
  activities: [
    {
      activityName: '',
      activityEndTime: '',
      activityEffect: '',
    }
  ],
  imageUrl: '',
  clubEducationCaseAttachment: '',
  clubWorkIntroduction: ''
})
const detail = (data) => {
  console.log(data)
  choose.value = data.recordId
  clubEvaluationInfo.value.recordId = data.recordId
  clubEvaluationInfo.value.clubName = data.clubName
  clubEvaluationInfo.value.handoverMethod = data.handoverMethod === 0 ? '0' : '1'
  clubEvaluationInfo.value.handoverParticipantsCount = data.handoverParticipantsCount
  clubEvaluationInfo.value.meetings = data.meetings
  clubEvaluationInfo.value.guideTeacher = data.guideTeacher === 0 ? '0' : '1'
  clubEvaluationInfo.value.associationAwards = data.associationAwards
  clubEvaluationInfo.value.publicityManagementEffectiveness = data.publicityManagementEffectiveness
  clubEvaluationInfo.value.hostedSchoolLevelActivities = data.hostedSchoolLevelActivities
  clubEvaluationInfo.value.activities = data.activities
  clubEvaluationInfo.value.clubWorkIntroduction = data.clubWorkIntroduction
  clubEvaluationInfo.value.declarationYear = data.declarationYear
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubAwardInfo, data.recordId)
  eventEmitter.on(APIEventEnum.getClubAwardInfoSuccess, 'getClubAwardInfoSuccess', (data) => {
    clubEvaluationInfo.value.totalMembers = data.totalMembers
    clubEvaluationInfo.value.backboneNumber = data.backboneNumber
    clubEvaluationInfo.value.communistRelatedBackBoneNumber = data.communistRelatedBackBoneNumber
    clubEvaluationInfo.value.administrativeGuideTeacherName = data.administrativeGuideTeacherName
    clubEvaluationInfo.value.businessGuideTeacherName = data.businessGuideTeacherName
    clubEvaluationInfo.value.isFinancialInformationPublic = data.isFinancialInformationPublic ? '是' : '否'
    clubEvaluationInfo.value.imageUrl = data.imageUrl
    clubEvaluationInfo.value.clubEducationCaseAttachment = data.clubEducationCaseAttachment
  })
}

eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluations)
eventEmitter.on(APIEventEnum.getClubEvaluationsSuccess, 'getClubEvaluationsSuccess', (data) => {
  evaluations.value = data.filter(evaluation => { return evaluation.status === null })
})

const passClubAwardReview = () => {
  if (clubEvaluationInfo.value.recordId === null) {
    NotifyPlugin.warning({
      title: '操作失败',
      content: '请选择任一社团评优信息！',
    })
    return
  }
  eventEmitter.emit(APIEventEnum.request, APIEnum.passClubAwardReview, { recordId: clubEvaluationInfo.value.recordId })
}

const unPassClubAwardReview = () => {
  if (clubEvaluationInfo.value.recordId === null) {
    NotifyPlugin.warning({
      title: '操作失败',
      content: '请选择任一社团评优信息！',
    })
    return
  }
  eventEmitter.emit(APIEventEnum.request, APIEnum.unPassClubAwardReview, { recordId: clubEvaluationInfo.value.recordId })
}

eventEmitter.on(APIEventEnum.passClubAwardReviewSuccess, 'passClubAwardReviewSuccess', () => {
  NotifyPlugin.success({
    title: '操作成功',
    content: '通过社团评优成功',
  })
})

eventEmitter.on(APIEventEnum.unPassClubAwardReviewSuccess, 'unPassClubAwardReviewSuccess', () => {
  NotifyPlugin.info({
    title: '操作成功',
    content: '驳回社团评优成功',
  })
})



onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getClubEvaluationsSuccess, 'getClubEvaluationsSuccess')
  eventEmitter.off(APIEventEnum.getClubAwardInfoSuccess, 'getClubAwardInfoSuccess')
  eventEmitter.off(APIEventEnum.passClubAwardReviewSuccess, 'passClubAnnualReviewSuccess')
  eventEmitter.off(APIEventEnum.unPassClubAwardReviewSuccess, 'unPassClubAnnualReviewSuccess')
})

</script>
