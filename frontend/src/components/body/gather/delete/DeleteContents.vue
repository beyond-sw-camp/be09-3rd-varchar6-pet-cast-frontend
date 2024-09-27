<template>
    <b-form-group label="모임 내용" label-for="nameInput">
        <b-form-textarea
            id="nameInput"
            :value="contents"
            required
            placeholder="모임 내용"
            readonly
            style="resize: none;"
            class="contents"
        ></b-form-textarea>
    </b-form-group>
</template>

<script setup>
    
    defineProps({
        contents:
        {
            type: String,
            required: true
        }
    });
</script>

<style scoped>
    .contents {
        height: 200px;
    }
    .contents b-form-textarea {
        width: 100%;
        resize: none;
        overflow-y: auto;
    }
</style>