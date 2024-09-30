<template>
    <b-card title="모임 정보" class="card">
                <ModifyMember :members="gatherDetail?.members || []" />
                
                <ModifyContents :contents="gatherDetail?.contents"/>
                
                <ModifyUrl :url="gatherDetail?.url"/>
                
                <ModifyDate :createdAt="gatherDetail?.createAt"/>
            </b-card>
</template>

<script setup>
    import ModifyMember from './ModifyMember.vue';
    import ModifyContents from './ModifyContents.vue';
    import ModifyUrl from './ModifyUrl.vue';
    import ModifyDate from './ModifyDate.vue';

    defineProps({
        gatherDetail:
        {
            type: Object,
            required: true
        }
    });
</script>

<style scoped>
    .card {
        background-color: #DDF5FF;
    }
</style>