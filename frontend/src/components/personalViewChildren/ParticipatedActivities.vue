<style scoped>
.mainBox {
    display: flex;
    width: 100%;
    height: 600px;
}

/* 左边部分 */
.leftSide {
    width: 80%;
    padding: 5px;
}

/* 左边部分内容 */
.leftSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
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

/* 右边部分 */
.rightSide {
    width: 20%;
    padding: 5px;
}

/* 右边部分内容 */
.rightSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
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
</style>

<template>
    <div class="mainBox">
        <div class="leftSide">
            <t-card style="height: 100%;">
                <template #header>
                    <t-radio-group :default-value="defaultFormat" @change="groupChangeFn">
                        <t-radio name="radio" value=".docx" label="导出为word" />
                        <t-radio name="radio" value=".pdf" label="导出为pdf" />
                    </t-radio-group>
                    <t-button theme="primary" @click="exportFile">导出</t-button>
                </template>
                <template #content>
                    <t-table :data="achievements" row-key="index" :columns="columns"
                        :selected-row-keys="selectedRowKeys" :select-on-row-click="selectOnRowClick" lazy-load
                        @select-change="handleSelectedAchievements">
                    </t-table>
                </template>
            </t-card>
        </div>

        <div class="rightSide">
            <div class="rightSideContent">

            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import store from '@/store';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import '@/utils/simhei-normal'
import { Document, Packer, Paragraph, Table, TableCell, TableRow, WidthType } from 'docx';
import { saveAs } from 'file-saver';

// 获取的成就
const achievements = ref(store.state.userInfo.achievements)

// 选中的成就
const selectedRowKeys = ref([]);
const checkedAchievements = ref([])
const selectOnRowClick = ref(true);
const columns = [
    { colKey: 'row-select', type: 'multiple', width: 50, },
    { colKey: 'activityName', title: '活动名称', width: '100' },
    { colKey: 'type', title: '活动类型', width: '100' },
    { colKey: 'personalEffect', title: '奖项名称', width: '120' },
    { colKey: 'awardWiningTime', title: '获奖时间', ellipsis: true },
    {
        colKey: 'operate',
        width: 340,
        title: '操作',
    }
];

const handleSelectedAchievements = (value, ctx) => {
    selectedRowKeys.value = value
    checkedAchievements.value = ctx.selectedRowData
}

// 导出选中的成就成word或者pdf的形式
const defaultFormat = ref('.docx')
const format = ref('.docx')
const groupChangeFn = (value) => {
    format.value = value
}

const exportFile = () => {
    if (checkedAchievements.value.length === 0) {
        return
    }
    if (format.value === '.docx') {
        const table = new Table({
            rows: [
                new TableRow({
                    children: [
                        new TableCell({
                            children: [new Paragraph('活动名称')],
                            width: { size: 150, type: WidthType.DXA },
                        }),
                        new TableCell({
                            children: [new Paragraph('成就')],
                            width: { size: 150, type: WidthType.DXA },
                        }),
                        new TableCell({
                            children: [new Paragraph('获取时间')],
                            width: { size: 150, type: WidthType.DXA },
                        }),
                    ],
                }),
                ...checkedAchievements.value.map(
                    (item) =>
                        new TableRow({
                            children: [
                                new TableCell({
                                    children: [new Paragraph(item.activityName)],
                                    width: { size: 300, type: WidthType.DXA },
                                }),
                                new TableCell({
                                    children: [new Paragraph(item.personalEffect)],
                                    width: { size: 300, type: WidthType.DXA },
                                }),
                                new TableCell({
                                    children: [new Paragraph(item.awardWiningTime)],
                                    width: { size: 300, type: WidthType.DXA },
                                }),
                            ],
                        })
                ),
            ],
        });

        const doc = new Document({
            sections: [
                {
                    children: [table]
                }
            ]
        })

        Packer.toBlob(doc).then((blob) => {
            saveAs(blob, 'achievement.docx')
        })

    } else if (format.value === '.pdf') {
        const doc = new jsPDF()
        doc.setFont('simhei')
        doc.autoTable({
            head: [['活动名称', '成就', '获取时间']],
            body: checkedAchievements.value.map(item => [item.activityName, item.personalEffect, item.awardWiningTime]),
            styles: {
                font: 'simhei',
                fontSize: 12,
            },
            columnStyles: {
                0: { cellWidth: 'auto' },
                1: { cellWidth: 'auto' },
                2: { cellWidth: 'auto' },
            },
        })
        doc.save('achievements.pdf')
    }
}

</script>