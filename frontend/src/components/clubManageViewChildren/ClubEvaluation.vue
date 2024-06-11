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

.attachContainer {
    width: 80%;
    margin: 0 auto 10px;
}
</style>

<template>
    <div class="clubEvaluationContainer">
        <div class="titleContainer">优秀学生社团申请表</div>
        <div class="tableContainer">
            <t-row id="border">
                <t-row>
                    <t-col :span="3">
                        <div class="text">学生社团名称</div>
                    </t-col>
                    <t-col :span="9" id="table">
                        {{ clubEvaluation.clubName }}
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">学生社团总人数</div>
                    </t-col>
                    <t-col :span="3" id="table">
                        {{ clubEvaluation.totalMembers }}
                    </t-col>
                    <t-col :span="3" id="table">
                        <div class="text">学生社团骨干人数</div>
                    </t-col>
                    <t-col :span="3" id="table">
                        <t-input v-model="clubEvaluation.backboneNumber" style="width: 60%;" borderless
                            align="center" />
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="9">
                        <div class="text">学生骨干是中共党员、入党积极分子或提交入党申请书人数</div>
                    </t-col>
                    <t-col :span="3" id="table">
                        <t-input v-model="clubEvaluation.communistRelatedBackBoneNumber" style="width: 60%;" borderless
                            align="center" />
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">行政指导老师</div>
                    </t-col>
                    <t-col :span="3" id="table">
                        {{ clubEvaluation.administrativeGuideTeacherName }}
                    </t-col>
                    <t-col :span="3" id="table">
                        <div class="text">业务指导老师</div>
                    </t-col>
                    <t-col :span="3" id="table">
                        {{ clubEvaluation.businessGuideTeacherName }}
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
                                <t-radio-group v-model="clubEvaluation.handoverMethod" @change="onhandoverMethodChange">
                                    <t-radio value="stuffMeeting">全员大会</t-radio>
                                    <t-radio value="others">其他</t-radio>
                                </t-radio-group>
                            </t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col :span="12">
                                <t-input v-model="clubEvaluation.handoverParticipantsCount" style="width: 60%;"
                                    borderless />
                            </t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col :span="12">
                                <t-radio-group v-model="clubEvaluation.advisorParticipation"
                                    @change="onadvisorParticipationChange">
                                    <t-radio value="1">是</t-radio>
                                    <t-radio value="0">否</t-radio>
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
                                {{ clubEvaluation.isFinancialInformationPublic ? '是' : '否' }}
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
                        <div v-for="(item, index) in clubEvaluation.meetings">
                            <t-popup placement="left-bottom">
                                <template #content>
                                    <t-button shape="square" variant="text" @click="addMettings">
                                        <t-icon name="add" />
                                    </t-button>
                                    <t-button shape="square" variant="text" @click="deleteMettings(index)">
                                        <t-icon name="delete" />
                                    </t-button>
                                </template>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="4">
                                        <t-input v-model="item.meetingTime" style="width: 60%;" borderless />
                                    </t-col>
                                    <t-col id="table" :span="4">
                                        <t-input v-model="item.location" style="width: 60%;" borderless />
                                    </t-col>
                                    <t-col id="table" :span="4">
                                        <t-select v-model="item.category" style="width: 60%;">
                                            <t-option label="成员大会" :value="1" />
                                            <t-option label="骨干例会" :value="0" />
                                        </t-select>
                                    </t-col>
                                </t-row>
                            </t-popup>
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团获奖情况（只填写市级、省级、国家级荣誉）</div>
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
                        <div v-for="(item, index) in clubEvaluation.associationAwards">
                            <t-popup placement="left-bottom">
                                <template #content>
                                    <t-button shape="square" variant="text" @click="addAssociationAwards">
                                        <t-icon name="add" />
                                    </t-button>
                                    <t-button shape="square" variant="text" @click="deleteAssociationAwards(index)">
                                        <t-icon name="delete" />
                                    </t-button>
                                </template>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="4">
                                        <t-input v-model="item.awardName" style="width: 60%;" borderless />
                                    </t-col>
                                    <t-col id="table" :span="4">
                                        <t-input v-model="item.awardTime" style="width: 60%;" borderless />
                                    </t-col>
                                    <t-col id="table" :span="4">
                                        <t-input v-model="item.issuingAuthority" style="width: 60%;" borderless />
                                    </t-col>
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
                            <t-col id="table" :span="4">
                                <t-input v-model="clubEvaluation.publicityManagementEffectiveness.submissionsCount"
                                    style="width: 60%;" borderless />
                            </t-col>
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
                                    v-for="(item, index) in clubEvaluation.publicityManagementEffectiveness.PublicityAboveSchoolLevel">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text"
                                                @click="addPublicityAboveSchoolLevel">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text"
                                                @click="deletePublicityAboveSchoolLevel(index)">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row id="table">
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.platform" style="width: 60%;" borderless />
                                            </t-col>
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.content" style="width: 60%;" borderless />
                                            </t-col>
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
                                <div v-for="(item, index) in clubEvaluation.hostedSchoolLevelActivities.schoolLv">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text" @click="addSchoolLv">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text" @click="deleteSchoolLv(index)">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row :id="index === 0 ? '' : 'table'">
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.host" style="width: 60%;" borderless />
                                            </t-col>
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.activityName" style="width: 60%;" borderless />
                                            </t-col>
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
                                <div v-for="(item, index) in clubEvaluation.hostedSchoolLevelActivities.municipal">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text" @click="addMunicipal">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text" @click="deleteMunicipal(index)">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row :id="index === 0 ? '' : 'table'">
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.host" style="width: 60%;" borderless />
                                            </t-col>
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.activityName" style="width: 60%;" borderless />
                                            </t-col>
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
                                <div v-for="(item, index) in clubEvaluation.hostedSchoolLevelActivities.provincial">
                                    <t-popup placement="left-bottom">
                                        <template #content>
                                            <t-button shape="square" variant="text" @click="addProvincial">
                                                <t-icon name="add" />
                                            </t-button>
                                            <t-button shape="square" variant="text" @click="deleteProvincial(index)">
                                                <t-icon name="delete" />
                                            </t-button>
                                        </template>
                                        <t-row :id="index === 0 ? '' : 'table'">
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.host" style="width: 60%;" borderless />
                                            </t-col>
                                            <t-col id="table" :span="6">
                                                <t-input v-model="item.activityName" style="width: 60%;" borderless />
                                            </t-col>
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
                        <div v-for="(item, index) in clubEvaluation.activities">
                            <t-popup placement="left-bottom">
                                <template #content>
                                    <t-button shape="square" variant="text" @click="addActivity">
                                        <t-icon name="add" />
                                    </t-button>
                                    <t-button shape="square" variant="text" @click="deleteActivity(index)">
                                        <t-icon name="delete" />
                                    </t-button>
                                </template>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="4">
                                        <t-input v-model="item.activityName" style="width: 60%;" borderless />
                                    </t-col>
                                    <t-col id="table" :span="4">
                                        <t-input v-model="item.activityEndTime" style="width: 60%;" borderless />
                                    </t-col>
                                    <t-col id="table" :span="4">
                                        <t-input v-model="item.activityEffect" style="width: 60%;" borderless />
                                    </t-col>
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
                        <t-upload :size-limit="{ size: 3000000, unit: 'B' }" accept=".doc,.docx,.docm,.dot,.dotx,.dotm"
                            :auto-upload="false" :onSelectChange="selectFileChangeHandler" @validate="onValidate">
                        </t-upload>
                        <div style="color:#2f2f2f; font-size: 12px; margin-left: 5px">
                            另附页提交，详见附录要求
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团代表性照片</div>
                    </t-col>
                    <t-col style="padding: 10px 0" :span="9" id="table">
                        <t-upload :image-viewer-props="{ closeOnEscKeydown: false }"
                            :size-limit="{ size: 500, unit: 'KB' }" theme="image" accept="image/*" :auto-upload="false"
                            :show-image-file-name="true" :locale="{
                                triggerUploadText: {
                                    image: '请选择图片',
                                },
                            }" :onSelectChange="selectChangeHandler">
                        </t-upload>
                        <div style="color:#2f2f2f; font-size: 12px; margin-left: 5px">
                            请提供一张高清照片，照片以学生社团名称命名
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="text">社团工作简介</div>
                    </t-col>
                    <t-col :span="9" id="table">
                        <t-textarea v-model="clubEvaluation.clubWorkIntroduction" placeholder="（2022年5月至今，不超200字）"
                            :autosize="{ minRows: 5, maxRows: 5 }" />
                    </t-col>
                </t-row>
            </t-row>
        </div>
        <div style="margin:50px auto 50px auto;width: 100%;display: flex;justify-content: center; align-items: center;">
            <t-button @click="submitClubEvaluation">提交</t-button>
        </div>
        <div class="attachContainer">
            <ul style="list-style: none;">
                <h2>附录：学生社团育人成效案例撰写指引</h2>
                <li>
                    <h3>一、标题</h3>
                    <p>学生社团名称：15字以内标题（方正小标宋，小二，居中）
                        （例：国旗班：守护国旗，厚植家国情怀）</p>
                </li>
                <li>
                    <h3>二、正文</h3>
                    <ol>
                        <li>摘要：高度凝练学生社团的举措和育人成效，字数150字以内。</li>
                        <li>简要阐述特色做法和育人效果，字数400字以内。</li>
                        <li>全篇行间距1.5倍，正文内容每段统一空两格，宋体，小四。</li>
                    </ol>
                </li>
                <li>
                    <h3> 三、撰写要求</h3>
                    <ol>
                        <li>
                            紧密围绕立德树人和学校人才培养目标，不同类别学生社团育人成效可着重从以下方面考查：
                            <ul>
                                <li>思想政治类社团注重学习传播马克思主义和马克思主义中国化理论成果，特别是习近平新时代中国特色社会主义思想活动开展情况和成效</li>
                                <li>学术科技类社团注重提高学生学习能力、促进学生学业水平的活动开展情况和成效</li>
                                <li>体育竞技类社团注重增强学生体质健康水平的活动开展情况和成效</li>
                                <li>志愿公益类社团注重增强学生劳动能力、培养学生家国情怀等方面活动开展情况和成效</li>
                                <li>文化艺术类社团注重繁荣校园文化、传播中华优秀传统文化等方面的活动开展情况和成效</li>
                            </ul>
                        </li>
                        <li>学生社团党建以及临时团支部工作方面的描述。</li>
                        <li>每个社团聚焦一个项目、活动或主题，也可以全面反映社团特色。内容要高度凝练，结构层次清晰，或用数据（数据可用表格、图表展示），或用事实，确保论述清晰明确。</li>
                    </ol>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { MessagePlugin } from 'tdesign-vue-next';
import { onMounted, onUnmounted, reactive } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const clubId = route.params.cid;

const clubEvaluation = reactive({
    clubName: '',
    totalMembers: '',
    backboneNumber: '',
    communistRelatedBackBoneNumber: '',
    administrativeGuideTeacherName: '',
    businessGuideTeacherName: '',
    handoverMethod: '',
    handoverParticipantsCount: '',
    advisorParticipation: '',
    isFinancialInformationPublic: '',
    meetings: [
        { meetingTime: '', location: '', category: '' }
    ],
    associationAwards: [
        { awardName: '', awardTime: '', issuingAuthority: '' }
    ],
    publicityManagementEffectiveness: {
        submissionsCount: '',
        PublicityAboveSchoolLevel: [
            { platform: '', content: '' }
        ]
    },
    hostedSchoolLevelActivities: {
        schoolLv: [
            { host: '', activityName: '' },
        ],
        municipal: [
            { host: '', activityName: '' },
        ],
        provincial: [
            { host: '', activityName: '' },
        ]
    },
    activities: [
        { activityName: '', activityEndTime: '', activityEffect: '' },
    ],
    clubEducationCaseAttachment: '',
    imageUrl: '',
    clubWorkIntroduction: '',
})

const clubEvaluationValidate = () => {
    let flag = true
    if (clubEvaluation.backboneNumber === '') {
        flag = false
        MessagePlugin.warning('请输入骨干人数')
    } else if (clubEvaluation.communistRelatedBackBoneNumber === '') {
        flag = false
        MessagePlugin.warning('请输入骨干是中共党员、入党积极分子或提交入党申请书人数')
    }
    else if (clubEvaluation.handoverMethod === '') {
        flag = false
        MessagePlugin.warning('请输入换届方式')
    } else if (clubEvaluation.handoverParticipantsCount === '') {
        flag = false
        MessagePlugin.warning('请输入换届参与人数')

    } else if (clubEvaluation.advisorParticipation === '') {
        flag = false
        MessagePlugin.warning('请输入指导老师是否参与')
    } else if (clubEvaluation.meetings.length === 0) {
        flag = false
        MessagePlugin.warning('请输入会议情况')
    } else if (clubEvaluation.associationAwards.length === 0) {
        flag = false
        MessagePlugin.warning('请输入社团获奖情况')
    } else if (clubEvaluation.publicityManagementEffectiveness.submissionsCount === '' && clubEvaluation.publicityManagementEffectiveness.PublicityAboveSchoolLevel.length === 0) {
        flag = false
        MessagePlugin.warning('请输入宣传管理及成效')
    } else if (clubEvaluation.clubWorkIntroduction === '') {
        flag = false
        MessagePlugin.warning('请输入社团工作介绍')
    }
    return flag
}
// 换届情况
const onhandoverMethodChange = (value) => {
    clubEvaluation.handoverMethod = value
}

const onadvisorParticipationChange = (value) => {
    clubEvaluation.advisorParticipation = value
}

// 全员大会、骨干例会情况
const addMettings = () => {
    clubEvaluation.meetings.push({ meetingTime: '', location: '', category: '' })
}

const deleteMettings = (index) => {
    if (clubEvaluation.meetings.length > 1)
        clubEvaluation.meetings.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}

// 社团获奖情况
const addAssociationAwards = () => {
    clubEvaluation.associationAwards.push({ awardName: '', awardTime: '', issuingAuthority: '' })
}

const deleteAssociationAwards = (index) => {
    if (clubEvaluation.associationAwards.length > 1)
        clubEvaluation.associationAwards.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}

// 宣传管理及成效
const addPublicityAboveSchoolLevel = () => {
    clubEvaluation.publicityManagementEffectiveness.PublicityAboveSchoolLevel.push({ platform: '', content: '' })
}

const deletePublicityAboveSchoolLevel = (index) => {
    if (clubEvaluation.publicityManagementEffectiveness.PublicityAboveSchoolLevel.length > 1)
        clubEvaluation.publicityManagementEffectiveness.PublicityAboveSchoolLevel.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}


// 学生社团承办校级以上活动主办方及活动名称
const addSchoolLv = () => {
    clubEvaluation.hostedSchoolLevelActivities.schoolLv.push({ host: '', activityName: '' })
}

const deleteSchoolLv = (index) => {
    if (clubEvaluation.hostedSchoolLevelActivities.schoolLv.length > 1)
        clubEvaluation.hostedSchoolLevelActivities.schoolLv.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}

const addMunicipal = () => {
    clubEvaluation.hostedSchoolLevelActivities.municipal.push({ host: '', activityName: '' })
}

const deleteMunicipal = (index) => {
    if (clubEvaluation.hostedSchoolLevelActivities.municipal.length > 1)
        clubEvaluation.hostedSchoolLevelActivities.municipal.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}

const addProvincial = () => {
    clubEvaluation.hostedSchoolLevelActivities.provincial.push({ host: '', activityName: '' })
}

const deleteProvincial = (index) => {
    if (clubEvaluation.hostedSchoolLevelActivities.provincial.length > 1)
        clubEvaluation.hostedSchoolLevelActivities.provincial.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}

// 学生社团活动情况
const addActivity = () => {
    clubEvaluation.activities.push({ activityName: '', activityTime: '', activityEffect: '' })
}

const deleteActivity = (index) => {
    if (clubEvaluation.activities.length > 1)
        clubEvaluation.activities.splice(index, 1)
    else
        MessagePlugin.error('至少保留一项')
}

const onValidate = (context) => {
    if (context.type === 'FILE_OVER_SIZE_LIMIT') {
        Message.warning('文件大小超出上限');
    }
};

const selectFileChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadFile, { file: fileList[0] })
}

const selectChangeHandler = (fileList) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadImage, { file: fileList[0] })
}

const submitClubEvaluation = () => {
    if (clubEvaluationValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postClubEvaluationForm, { clubId, ...clubEvaluation })
    }
}

onMounted(() => {
    // 获取社团信息
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluateInfo, { clubId })

    eventEmitter.emit(APIEventEnum.request, APIEnum.getAssociationAwards, { clubId })

    eventEmitter.emit(APIEventEnum.request, APIEnum.getMeetings, { clubId })

    eventEmitter.on(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess', (data) => {
        clubEvaluation.clubName = data.clubName
        clubEvaluation.totalMembers = data.totalMembers
        clubEvaluation.administrativeGuideTeacherName = data.administrativeGuideTeacherName
        clubEvaluation.businessGuideTeacherName = data.businessGuideTeacherName
        clubEvaluation.isFinancialInformationPublic = data.isFinancialInformationPublic
    })
    eventEmitter.on(APIEventEnum.uploadFileSuccess, 'uploadFileSuccess', (data) => {
        clubEvaluation.clubEducationCaseAttachment = data.url
        MessagePlugin.success('文件上传成功')
    })
    eventEmitter.on(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess', (data) => {
        clubEvaluation.imageUrl = data.url
        MessagePlugin.success('图片上传成功')
    })
    eventEmitter.on(APIEventEnum.postClubEvaluationFormSuccess, 'postClubEvaluationFormSuccess', () => {
        MessagePlugin.success('提交成功')
    })
    eventEmitter.on(APIEventEnum.getAssociationAwardsSuccess, 'getAssociationAwardsSuccess', (data) => {
        if (data.length > 0) {
            clubEvaluation.associationAwards.pop()
            clubEvaluation.associationAwards.push(...data)
        }
    })
    eventEmitter.on(APIEventEnum.getMeetingsSuccess, 'getMeetingsSuccess', (data) => {
        if (data.length > 0) {
            clubEvaluation.meetings.pop()
            clubEvaluation.meetings.push(...data)
        }
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess')
    eventEmitter.off(APIEventEnum.uploadFileSuccess, 'uploadFileSuccess')
    eventEmitter.off(APIEventEnum.uploadImageSuccess, 'uploadImageSuccess')
    eventEmitter.off(APIEventEnum.postClubEvaluationFormSuccess, 'postClubEvaluationFormSuccess')
    eventEmitter.off(APIEventEnum.getAssociationAwardsSuccess, 'getAssociationAwardsSuccess')
    eventEmitter.off(APIEventEnum.getMeetingsSuccess, 'getMeetingsSuccess')
})
</script>