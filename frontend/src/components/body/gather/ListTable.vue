<template>
    <div class="gatherlist">
        <b-table 
            striped 
            hover 
            :items="items" 
            :fields="fields"
            @row-clicked="onRowClicked"
        ></b-table>
    </div>
</template>

<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({
        items: Array,
        fields: Array
    });

    const emit = defineEmits(['rowClicked']);

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