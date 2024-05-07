<style scoped>
/* 总容器 */
.container {
    display: flex;
    flex-direction: column;
    height: 670px;
    overflow: hidden;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f7f7f7;
    padding: 20px;
    box-sizing: border-box;
}

/* 上半部分 */
.upper-section {
    display: flex;
    justify-content: space-between;
    flex: 1;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

/* 主席框 */
.president-section {
    width: 400px;
    height: 400px;
    border-radius: 8px;
    text-align: center;
    padding: 20px;
    box-sizing: border-box;
    background-color: #f0f0f0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.president-section img {
    max-width: 100%;
    max-height: 200px;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 其他干部框 */
.executive-section {
    flex: 1;
    margin-left: 50px;
    height: 400px;
    border-radius: 8px;
    padding: 20px;
    box-sizing: border-box;
    background-color: #f0f0f0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow-x: auto;
    white-space: nowrap;
}

/* 横向滚动条样式 */
.executive-section::-webkit-scrollbar {
    height: 8px;
    /* 修改滚动条宽度为6px */
    background-color: transparent;
}

.executive-section::-webkit-scrollbar-thumb {
    background-color: #999;
    border-radius: 4px;
    transition: background-color 0.3s ease;
}

.executive-section::-webkit-scrollbar-thumb:hover,
.executive-section::-webkit-scrollbar-thumb:active {
    background-color: #555;
}

.executive-section div {
    display: inline-block;
    width: 200px;
    height: 250px;
    border-radius: 8px;
    text-align: center;
    padding: 10px;
    box-sizing: border-box;
    margin-right: 20px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.executive-section div img {
    max-width: 100%;
    max-height: 150px;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 下半部分 */
.member-section {
    padding: 20px;
    box-sizing: border-box;
    text-align: center;
    background-color: #fff;
    border-radius: 8px;
    margin-top: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.charts {
    margin-top: 20px;
}

/* 新增样式 */
h2,
h3,
h4 {
    color: #333;
}

p {
    color: #666;
}
</style>

<template>
    <div class="container">
        <div class="upper-section">
            <div class="president-section">
                <img :src="president.image" />
                <h2>{{ president.name }}</h2>
                <p>联系方式: {{ president.phone }}</p>
            </div>

            <div class="executive-section">
                <h3>其他干部</h3>
                <div v-for="executive in executives" :key="executive.name">
                    <img :src="executive.image" :alt="executive.name" />
                    <h4>{{ executive.name }}</h4>
                    <p>职位: {{ executive.position }}</p>
                </div>
            </div>
        </div>

        <div class="member-section">
            <h3>社团成员</h3>
            <p>成员数量: {{ memberCount }}</p>
            <p>成员组成: {{ memberComposition }}</p>
            <div class="charts">
                <!-- 这里可以插入图表组件 -->
            </div>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';

const president = ref({})
const executives = ref([]);

const memberCount = ref(0);
const memberComposition = ref('');

eventEmitter.emit(APIEventEnum.request, APIEnum.getClubMembers, { clubID: store.state.clubID })

eventEmitter.on(APIEventEnum.getClubMembersSuccess, (members) => {
    president.value = members.president
    executives.value = members.executives
    memberCount.value = members.others.number
    memberComposition.value = '干部：' + (1 + members.executives.length) + '人，普通成员：' + members.others.number + '人'
})

</script>