<template>
    <b-card title="모임 정보" class="card">
        <DeleteMember :members="gatherDetail?.members || []"/>
                
        <DeleteContents :contents="gatherDetail?.contents"/>

        <DeleteUrl :url="gatherDetail?.url"/>

        <DeleteDate :createdAt="gatherDetail?.createAt"/>
            </b-card>
</template>

<script setup>
    import DeleteMember from './DeleteMember.vue';
    import DeleteContents from './DeleteContents.vue';
    import DeleteUrl from './DeleteUrl.vue';
    import DeleteDate from './DeleteDate.vue';


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