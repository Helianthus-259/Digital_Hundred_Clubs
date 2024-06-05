<style scoped>
.AnnualAuditContainer {
    width: 100%;
    height: 690px;
    background: #ffffff;
    border-radius: 10px;
    overflow-y: auto;
}

/*滚动条样式*/
.AnnualAuditContainer::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.AnnualAuditContainer::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.AnnualAuditContainer::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.AnnualAuditContainer::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.AnnualAuditTitle {
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

.txt {
    font-size: 14px;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 10px;
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

.downloadContainer {
    width: 80%;
    margin: 0 auto 50px auto;
}
</style>

<template>
    <div class="AnnualAuditContainer">
        <div class="AnnualAuditTitle">学生社团年审申报表</div>
        <div class="tableContainer">
            <t-row id="border">
                <t-row>
                    <t-col :span="3">
                        <div class="txt">学生社团</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.clubName }}</t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">学生社团类别</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.clubCategory }}</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">业务指导单位</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.responsibleDepartment }}</t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">学生社团总人数</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.totalMembers }}</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">行政指导老师</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.administrativeGuideTeacherName }}</t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">业务指导老师</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.businessGuideTeacherName }}</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">学生社团分布校园（区）</div>
                    </t-col>
                    <t-col id="table" :span="9">
                        <t-checkbox-group style="width: 80%; padding: 10px;" v-model="clubAnnualAudit.compus">
                            <t-checkbox :check-all="true" label="全选" />
                            <t-checkbox label="广州校区南校园" value="广州校区南校园" />
                            <t-checkbox label="广州校区东校园" value="广州校区东校园" />
                            <t-checkbox label="广州校区北校园" value="广州校区北校园" />
                            <t-checkbox label="珠海校区" value="珠海校区" />
                            <t-checkbox label="深圳校区" value="深圳校区" />
                        </t-checkbox-group>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">学生负责人姓名</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.contactPerson }}</t-col>
                    <t-col id="table" :span="3">
                        <div class="txt">学生负责人政治面貌</div>
                    </t-col>
                    <t-col id="table" :span="3">{{ clubAnnualAudit.politicalStatus }}</t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">宣传管理情况</div>
                    </t-col>
                    <t-col style="display: inline" :span="9">
                        <t-row style="border-left: 2px solid #000;">
                            <t-col :span="3">
                                <div class="txt">微信公众号</div>
                            </t-col>
                            <t-col style="display: block;" :span="9">
                                <t-row style="border-left: 2px solid #000;">
                                    <t-col :span="3">
                                        <div class="txt">名称</div>
                                    </t-col>
                                    <t-col id="table" :span="9">
                                        <t-input style="width: 90%;" placeholder="依托院系公众号的请填写院系公众号名称" borderless
                                            v-model="clubAnnualAudit.publicityManagementInfo.WeChatPublicAccount.name" />
                                    </t-col>
                                </t-row>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="3">
                                        <div class="txt">推送条数</div>
                                    </t-col>
                                    <t-col id="table" :span="9">
                                        <t-input style="width: 90%;" placeholder="" borderless
                                            v-model="clubAnnualAudit.publicityManagementInfo.WeChatPublicAccount.submitCount" />
                                    </t-col>
                                </t-row>
                                <t-row style="border-left: 2px solid #000;" id="table">
                                    <t-col :span="3">
                                        <div class="txt">三级审核人员</div>
                                    </t-col>
                                    <t-col style="display: block;" id="table" :span="9">
                                        <t-row>
                                            <t-col :span="4">
                                                <div class="txt">初审</div>
                                            </t-col>
                                            <t-col id="table" :span="4">
                                                <div class="txt">复审</div>
                                            </t-col>
                                            <t-col id="table" :span="4">
                                                <div class="txt">终审</div>
                                            </t-col>
                                        </t-row>
                                        <t-row id="table">
                                            <t-col :span="4">
                                                <t-input style="width: 90%;" placeholder="" borderless
                                                    v-model="clubAnnualAudit.publicityManagementInfo.WeChatPublicAccount.Auditors.name1" />
                                            </t-col>
                                            <t-col id="table" :span="4">
                                                <t-input style="width: 90%;" placeholder="" borderless
                                                    v-model="clubAnnualAudit.publicityManagementInfo.WeChatPublicAccount.Auditors.name2" />
                                            </t-col>
                                            <t-col id="table" :span="4">
                                                <t-input style="width: 90%;" placeholder="" borderless
                                                    v-model="clubAnnualAudit.publicityManagementInfo.WeChatPublicAccount.Auditors.name3" />
                                            </t-col>
                                        </t-row>
                                    </t-col>
                                </t-row>
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">网站</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.WebSite.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.WebSite.has === '1'"
                                    style="width: 60%;" placeholder="请输入网站名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.WebSite.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">微博</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.Blog.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.Blog.has === '1'"
                                    style="width: 60%;" placeholder="请输入微博名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.Blog.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">哔哩哔哩</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.BiliBili.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.BiliBili.has === '1'"
                                    style="width: 60%;" placeholder="请输入B站名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.BiliBili.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">抖音</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.TikTok.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.TikTok.has === '1'"
                                    style="width: 60%;" placeholder="请输入抖音名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.TikTok.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">微信视频号</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.WeChatVideoAccount.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.WeChatVideoAccount.has === '1'"
                                    style="width: 60%;" placeholder="请输入微信视频号名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.WeChatVideoAccount.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">快手</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.KuaiShou.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.KuaiShou.has === '1'"
                                    style="width: 60%;" placeholder="请输入快手名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.KuaiShou.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">刊物</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.Publication.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.Publication.has === '1'"
                                    style="width: 60%;" placeholder="请输入刊物名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.Publication.name" />
                            </t-col>
                        </t-row>
                        <t-row style="border-left: 2px solid #000;" id="table">
                            <t-col :span="3">
                                <div class="txt">其他</div>
                            </t-col>
                            <t-col id="table" :span="9">
                                <t-radio-group v-model="clubAnnualAudit.publicityManagementInfo.Other.has">
                                    <t-radio value="0">无</t-radio>
                                    <t-radio value="1">有</t-radio>
                                </t-radio-group>
                                <t-input v-show="clubAnnualAudit.publicityManagementInfo.Other.has === '1'"
                                    style="width: 60%;" placeholder="请输入名称" borderless
                                    v-model="clubAnnualAudit.publicityManagementInfo.Other.name" />
                            </t-col>
                        </t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">财务管理</div>
                    </t-col>
                    <t-col style="display: inline" :span="9">
                        <t-row>
                            <t-col id="table" :span="8">
                                <div class="txt">是否接受校外赞助（如有需填报附件3）</div>
                            </t-col>
                            <t-col id="table" :span="4">
                                <t-upload :size-limit="{ size: 3000000, unit: 'B' }"
                                    accept=".doc,.docx,.docm,.dot,.dotx,.dotm,.xls,.xlsx,.xlsm,.xlt,.xltx,.xltm,.xlsb,.xlam,.pdf"
                                    :auto-upload="false"
                                    :onSelectChange="(fileList) => selectFileChangeHandler(fileList, 'externalSponsorshipAttachment')"
                                    @validate="onValidate">
                                </t-upload>
                            </t-col>
                        </t-row>
                        <t-row id="table">
                            <t-col id="table" :span="8">
                                <div class="txt">是否向社团成员进行财务公开</div>
                            </t-col>
                            <t-col id="table" :span="4">{{ clubAnnualAudit.isFinancialInformationPublic === '0' ? '否' :
                                '是' }}</t-col>
                        </t-row>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">学生社团章程</div>
                    </t-col>
                    <t-col id="table" :span="9">
                        <t-upload :size-limit="{ size: 3000000, unit: 'B' }"
                            accept=".doc,.docx,.docm,.dot,.dotx,.dotm,.xls,.xlsx,.xlsm,.xlt,.xltx,.xltm,.xlsb,.xlam,.pdf"
                            :auto-upload="false"
                            :onSelectChange="(fileList) => selectFileChangeHandler(fileList, 'clubConstitutionAttachment')"
                            @validate="onValidate">
                        </t-upload>
                        <div style="color:#2f2f2f; font-size: 12px; margin-left: 5px">
                            请学生社团另行提供
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">会议及活动清单</div>
                    </t-col>
                    <t-col id="table" :span="9">
                        <t-upload :size-limit="{ size: 3000000, unit: 'B' }"
                            accept=".doc,.docx,.docm,.dot,.dotx,.dotm,.xls,.xlsx,.xlsm,.xlt,.xltx,.xltm,.xlsb,.xlam,.pdf"
                            :auto-upload="false"
                            :onSelectChange="(fileList) => selectFileChangeHandler(fileList, 'meetingActivityListAttachment')"
                            @validate="onValidate">
                        </t-upload>
                        <div style="color:#2f2f2f; font-size: 12px; margin-left: 5px">
                            包括全员大会、骨干会议、日常活动等，详见附件2
                        </div>
                    </t-col>
                </t-row>
                <t-row id="table">
                    <t-col :span="3">
                        <div class="txt">院系党组织/职能部门意见</div>
                    </t-col>
                    <t-col id="table" :span="9"></t-col>
                </t-row>
            </t-row>
        </div>
        <div style="margin:50px auto 50px auto;width: 100%;display: flex;justify-content: center; align-items: center;">
            <t-button @click="submitClubAnnualAuditForm" theme="primary" variant="outline">提交</t-button>
        </div>
        <div class="downloadContainer">
            <a style="display: block;" href="/download/附件2：2023年学生社团年审活动清单.xlsx" download>附件2：2023年学生社团年审活动清单</a>
            <a style="display: block;" href="/download/附件3：2023年学生社团年审校外赞助清单.xlsx" download>附件3：2023年学生社团年审校外赞助清单</a>
        </div>
    </div>
</template>

<script setup>
import { APIEventEnum, APIEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { MessagePlugin } from 'tdesign-vue-next';
import { onMounted, onUnmounted, reactive } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute()
const clubId = route.params.cid
const clubAnnualAudit = reactive({
    clubName: '',
    clubCategory: '',
    responsibleDepartment: '',
    totalMembers: '',
    administrativeGuideTeacherName: '',
    businessGuideTeacherName: '',
    compus: [],
    contactPerson: '',
    politicalStatus: '',
    isFinancialInformationPublic: '',
    publicityManagementInfo: {
        WeChatPublicAccount: { name: '', submitCount: '', Auditors: { name1: '', name2: '', name3: '' } },
        WebSite: { has: '0', name: '' },
        Blog: { has: '0', name: '' },
        BiliBili: { has: '0', name: '' },
        TikTok: { has: '0', name: '' },
        WeChatVideoAccount: { has: '0', name: '' },
        KuaiShou: { has: '0', name: '' },
        Publication: { has: '0', name: '' },
        Other: { has: '0', name: '' },
    },
    externalSponsorshipAttachment: '',
    clubConstitutionAttachment: '',
    meetingActivityListAttachment: '',
})

const onValidate = (context) => {
    if (context.type === 'FILE_OVER_SIZE_LIMIT') {
        Message.warning('文件大小超出上限');
    }
};

const selectFileChangeHandler = (fileList, flag) => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.uploadFile, { file: fileList[0], flag })
}

const submitClubAnnualAuditForm = () => {
    if (clubAnnualAudit.clubConstitutionAttachment === '' && clubAnnualAudit.meetingActivityListAttachment === '') {
        MessagePlugin.warning('请填写完整信息')
        return
    }
    eventEmitter.emit(APIEventEnum.request, APIEnum.postClubAnnualAuditForm, { clubId, ...clubAnnualAudit })
}

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getThisYearClubAnnual, { clubId: clubId, declarationYear: new Date().getFullYear() })
    eventEmitter.on(APIEventEnum.getThisYearClubAnnualSuccess, 'getThisYearClubAnnualSuccess', (data) => {
        clubAnnualAudit.clubName = data.clubName
        clubAnnualAudit.clubCategory = data.clubCategory
        clubAnnualAudit.responsibleDepartment = data.responsibleDepartment
        clubAnnualAudit.totalMembers = data.totalMembers
        clubAnnualAudit.administrativeGuideTeacherName = data.administrativeGuideTeacherName
        clubAnnualAudit.businessGuideTeacherName = data.businessGuideTeacherName
        clubAnnualAudit.contactPerson = data.contactPerson
        clubAnnualAudit.politicalStatus = data.politicalStatus
        clubAnnualAudit.isFinancialInformationPublic = data.isFinancialInformationPublic
    })
    eventEmitter.on(APIEventEnum.uploadFileSuccess, 'uploadFileSuccess', (data) => {
        if (data.flag === 'externalSponsorshipAttachment') {
            clubAnnualAudit.externalSponsorshipAttachment = data.url
        } else if (data.flag === 'clubConstitutionAttachment') {
            clubAnnualAudit.clubConstitutionAttachment = data.url
        } else if (data.flag === 'meetingActivityListAttachment') {
            clubAnnualAudit.meetingActivityListAttachment = data.url
        }
        MessagePlugin.success('上传成功')
    })
    eventEmitter.on(APIEventEnum.postClubAnnualAuditFormSuccess, 'postClubAnnualAuditFormSuccess', () => {
        MessagePlugin.success('提交成功')
    })
})

const downloadFile = (url, fileName) => {
    const link = document.createElement('a')
    link.href = url
    link.download = fileName // 指定下载后的文件名
    link.click()
}

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getThisYearClubAnnualSuccess, 'getThisYearClubAnnualSuccess')
    eventEmitter.on(APIEventEnum.uploadFileSuccess, 'uploadFileSuccess')
    eventEmitter.on(APIEventEnum.postClubAnnualAuditFormSuccess, 'postClubAnnualAuditFormSuccess')
})
</script>