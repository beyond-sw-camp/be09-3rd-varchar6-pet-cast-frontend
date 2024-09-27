<template>
    <b-card title="모임 정보" class="card">
                <DetailMember :members="gatherDetail?.members || []" />
                
                <DetailContents :contents="gatherDetail?.contents"/>
                
                <DetailUrl :url="gatherDetail?.url"/>
                
                <DetailDate :createdAt="gatherDetail?.createAt"
                            :updatedAt="gatherDetail?.updateAt"/>
<!-- <b-button type="submit" variant="primary">수정</b-button> -->
            </b-card>
</template>

<script setup>
    import DetailMember from '@/components/body/gather/detail/DetailMember.vue';
    import DetailContents from './DetailContents.vue';
    import DetailUrl from './DetailUrl.vue';
    import DetailDate from './DetailDate.vue';

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