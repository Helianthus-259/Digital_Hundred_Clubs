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

.text {
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}


.t-row {
  width: 100%;
  align-items: center;
}

.t-row#tableBorder {
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

.attachContainer {
  width: 80%;
  margin: 0 auto 10px;
}
</style>

<template>
  <t-aside style="width: 500px">
    <t-list stripe="true" style="max-height: 680px" :scroll="{ type: 'virtual' }">
      <t-list-item v-for="evaluation in evaluations" style="width: auto" :key="evaluation.recordId">
        <t-list-item-meta :title = "evaluation.clubName" :description="evaluation.declarationYear"/>
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
        <div class="titleContainer">优秀学生社团申请表</div>
        <div class="tableContainer">
          <t-row id="tableBorder">
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
                    <t-radio-group variant="primary-filled" :value="clubEvaluationInfo.handoverMethod === 0 ? '0' : '1'">
                      <t-radio-button value='0'>全员大会</t-radio-button>
                      <t-radio-button value='1'>其他</t-radio-button>
                    </t-radio-group>
                  </t-col>
                </t-row>
                <t-row id="table">
                  <t-col :span="12">{{clubEvaluationInfo.handoverParticipantsCount}}</t-col>
                </t-row>
                <t-row id="table">
                  <t-col :span="12">
                    <t-radio-group :value="clubEvaluationInfo.guideTeacher === 0 ? '0' : '1'">
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
                    {{ clubEvaluationInfo.isFinancialInformationPublic ? '是' : '否' }}
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
                <div v-for="(item, index) in clubEvaluationInfo.meetings">
                  <t-popup placement="left-bottom">
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="4">
                        {{item.time}}
                      </t-col>
                      <t-col id="table" :span="4">
                        {{item.location}}
                      </t-col>
                      <t-col id="table" :span="4">
                        <t-radio-group variant="primary-filled" :value="clubEvaluationInfo.handoverMethod === 0 ? '0' : '1'">
                          <t-radio-button value='0'>全员大会</t-radio-button>
                          <t-radio-button value='1'>骨干例会</t-radio-button>
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
                <div v-for="(item, index) in clubEvaluationInfo.associationAwards">
                  <t-popup placement="left-bottom">
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="4">{{item.name}}</t-col>
                      <t-col id="table" :span="4">{{item.time}}</t-col>
                      <t-col id="table" :span="4">{{item.organization}}</t-col>
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
                  <t-col id="table" :span="4">{{clubEvaluationInfo.publicityManagementEffectiveness.submissionsCount}}</t-col>
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
                        v-for="(item, index) in clubEvaluationInfo.publicityManagementEffectiveness.PublicityAboveSchoolLevel">
                      <t-popup placement="left-bottom">
                        <t-row id="table">
                          <t-col id="table" :span="6">{{item.platform}}</t-col>
                          <t-col id="table" :span="6">{{item.content}}</t-col>
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
                          <t-col id="table" :span="6">{{item.host}}</t-col>
                          <t-col id="table" :span="6">{{item.activityName}}</t-col>
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
                          <t-col id="table" :span="6">{{item.host}}</t-col>
                          <t-col id="table" :span="6">{{item.activityName}}</t-col>
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
                          <t-col id="table" :span="6">{{item.host}}</t-col>
                          <t-col id="table" :span="6">{{item.activityName}}</t-col>
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
                <div v-for="(item, index) in clubEvaluationInfo.activities">
                  <t-popup placement="left-bottom">
                    <t-row style="border-left: 2px solid #000;" id="table">
                      <t-col :span="4">{{item.activityName}}</t-col>
                      <t-col id="table" :span="4">{{item.activityTime}}</t-col>
                      <t-col id="table" :span="4">{{item.activityEffect}}</t-col>
                    </t-row>
                  </t-popup>
                </div>
              </t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团育人成效案例</div>
              </t-col>
              <t-col :span="9" id="table">{{clubEvaluationInfo.file}}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团代表性照片</div>
              </t-col>
              <t-col :span="9" id="table">{{clubEvaluationInfo.file}}</t-col>
            </t-row>
            <t-row id="table">
              <t-col :span="3">
                <div class="text">社团工作简介</div>
              </t-col>
              <t-col :span="9" id="table">
                <t-text>{{clubEvaluationInfo.clubWorkIntroduction}}</t-text>
              </t-col>
            </t-row>
          </t-row>
        </div>
      </div>
    </t-content>
    <t-footer>
      <t-space direction="horizontal" size="50%" style="margin-left: 15%">
        <t-button theme="danger">评优条件不符</t-button>
        <t-button theme="success">成为优秀社团</t-button>
      </t-space>
    </t-footer>
  </t-layout>
</template>

<script setup>
import {onUnmounted, ref, watch} from "vue";
import {ArrowDownIcon, ArrowRightIcon} from "tdesign-icons-vue-next";
import eventEmitter from "@/utils/eventEmitter.js";
import {APIEnum, APIEventEnum} from "@/Enum/index.js";


const evaluations = getEvaluationInfos()
const choose = ref(-1)
const theme = ["primary", "success"]
const icon = [ArrowDownIcon.stem, ArrowRightIcon.stem]
const clubEvaluationInfo = ref({
  clubName:'',
  handoverMethod:0,
  handoverParticipantsCount:100,
  isFinancialInformationPublic: 0,
  totalMembers:0,
  administrativeGuideTeacherName: '',
  businessGuideTeacherName: '',
  mainCompus: "",
  backboneNumber: 10,
  communistRelatedBackBoneNumber: 10,
  publicityManagementInfo:{
    wechatOfficial:'',
    web:'',
    weibo:'',
    bilibili:'',
    tiktok:'',
    wechatVideo:'',
    kuaishou:'',
    journal:'',
    other:'',
  },
  file: '',
  declarationYear:"Year",
  guideTeacher:0,
  meetings:[
    {
      activityId:0,
      clubId:0,
      time:"2022-1-1",
      location:"会议地点",
      staffMeetingOrbackBoneMeeting:0,//0全员大会,1骨干例会
      guideTeacher:1,
    }
  ],
  associationAwards:[
    {
      name:"xx奖",
      time:"2022-02-02",
      organization:"中山大学"
    }
  ],
  publicityManagementEffectiveness:{
    submissionsCount:5,
    PublicityAboveSchoolLevel:[
      {
        platform:"平台",
        content:"内容",
      }
    ],
  },
  hostedSchoolLevelActivities:{
    schoolLv:[
      {
        host:"主办方",
        activityName:"活动名",
      }
    ],
    municipal:[
      {
        host:"主办方",
        activityName:"活动名",
      }
    ],
    provincial:[
      {
        host:"主办方",
        activityName:"活动名",
      }
    ]
  },
  activities:[
    {
      activityName:"活动名称",
      activityTime:"活动时间",
      activityEffect:"活动成效",
    }
  ],
  clubWorkIntroduction:"工作简介"
})
const detail = (data) =>{
  console.log(data)
  choose.value = data.recordId
  clubEvaluationInfo.value.clubName = data.clubName
  clubEvaluationInfo.value.handoverMethod = data.handoverMethod
  clubEvaluationInfo.value.handoverParticipantsCount = data.handoverParticipantsCount
  clubEvaluationInfo.value.meetings = data.meetings
  clubEvaluationInfo.value.guideTeacher = data.guideTeacher
  clubEvaluationInfo.value.associationAwards = data.associationAwards
  clubEvaluationInfo.value.publicityManagementEffectiveness = data.publicityManagementEffectiveness
  clubEvaluationInfo.value.hostedSchoolLevelActivities = data.hostedSchoolLevelActivities
  clubEvaluationInfo.value.activities = data.activities
  clubEvaluationInfo.value.clubWorkIntroduction = data.clubWorkIntroduction
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluateInfo, { value: data.recordId })
  eventEmitter.on(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess', (data) => {
    clubEvaluationInfo.value.totalMembers = data.totalMembers
    clubEvaluationInfo.value.backboneNumber = data.backboneNumber
    clubEvaluationInfo.value.communistRelatedBackBoneNumber = data.communistRelatedBackBoneNumber
    clubEvaluationInfo.value.administrativeGuideTeacherName = data.administrativeGuideTeacherName
    clubEvaluationInfo.value.businessGuideTeacherName = data.businessGuideTeacherName
    clubEvaluationInfo.value.isFinancialInformationPublic = data.isFinancialInformationPublic
    console.log(clubEvaluationInfo.value)
  })
}


function getEvaluationInfos(total = 5) {
  const data = [];
  for (let i = 0; i < total; i++) {
    data.push({
      recordId:i,
      declarationYear:["2021","2022","2023"][i % 3],
      clubId:i,
      clubName:"xx社团",
      handoverMethod:[0, 1][i % 2],//0全员大会,1骨干例会
      handoverParticipantsCount:100,
      guideTeacher:[0, 1][i % 2],
      meetings:[
        {
          activityId:0,
          clubId:0,
          time:"2022-1-1",
          location:"会议地点",
          staffMeetingOrbackBoneMeeting:0,//0全员大会,1骨干例会
          guideTeacher:1,
        }
      ],
      associationAwards:[
        {
          name:"xx奖",
          time:"2022-02-02",
          organization:"中山大学"
        }
      ],
      publicityManagementEffectiveness:{
        submissionsCount:5,
        PublicityAboveSchoolLevel:[
          {
            platform:"平台",
            content:"内容",
          }
        ],
      },
      hostedSchoolLevelActivities:{
        schoolLv:[
          {
            host:"主办方",
            activityName:"活动名",
          }
        ],
        municipal:[
          {
            host:"主办方",
            activityName:"活动名",
          }
        ],
        provincial:[
          {
            host:"主办方",
            activityName:"活动名",
          }
        ]
      },
      activities:[
        {
          activityName:"活动名称",
          activityTime:"活动时间",
          activityEffect:"活动成效",
        }
      ],
      clubWorkIntroduction:"工作简介"
    });
  }
  return data;
}



onUnmounted(() => {
  eventEmitter.off(APIEventEnum.request, APIEnum.getClubEvaluateInfo)
  eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess')
})

</script>
