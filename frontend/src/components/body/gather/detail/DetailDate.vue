<template>
    <div class="dated">
        <b-row>
            <b-col md="6">
                <b-form-group label="생성 일자" label-for="createdAtInput">
                    <h6>{{ createdAt }}</h6>
                </b-form-group>
            </b-col>
            <b-col md="6">
                <b-form-group label="수정 일자" label-for="updatedAtInput">
                    <h6>{{ updatedAt }}</h6>
                </b-form-group>
            </b-col>
        </b-row>
    </div>    
</template>

<script setup>
    
    defineProps({
        createdAt:
        {
            type: String,
            required: true
        },
        updatedAt:
        {
            type: String,
            required: true
        }
    });
</script>

<style scoped>
    .dated {
        display: flex;
        align-items: center;
        justify-content: right;
    }
    .date b-form-textarea {
        width: 100%;
        resize: none;
        overflow-y: auto;
    }
</style>