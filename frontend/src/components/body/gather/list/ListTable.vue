<template>
    <div class="gatherlist">
        <b-table 
            striped 
            hover 
            :items="formattedItems" 
            :fields="fields"
            @row-clicked="onRowClicked"
        ></b-table>
    </div>
</template>

<script setup>
    import { defineProps, defineEmits, computed } from 'vue';

    const props = defineProps({
        items: Array,
        fields: Array
    });

    const emit = defineEmits(['rowClicked']);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        return `${year}년 ${month}월 ${day}일`;
    };

    const formattedItems = computed(() => {
        return props.items.map(item => ({
            ...item,
            생성일자: formatDate(item.생성일자)
        }));
    });


    const onRowClicked = (item, index, event) => {
        emit('rowClicked', item, index, event);
    };
</script>

<style scoped>
    .gatherlist {
        display: flex;
        justify-content: center;
        align-items: center;
        padding-left: 2rem;
        padding-right: 2rem; 
        flex-grow: 1; 
        overflow-y: auto;
    }
    .table {
        text-align: center;
    }
</style>