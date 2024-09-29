<template>
    <b-card title="초대장 정보" class="card">
        <br>
        <div class="np">
            <div class="input-group name">
                <span class="input-group-text" id="basic-addon1">성명</span>
                <input type="text" class="form-control" placeholder="성명을 입력하세요." aria-label="Username" aria-describedby="basic-addon1">
            </div>
            <div class="input-group phone">
                <span class="input-group-text" id="basic-addon1">연락처</span>
                <input type="text" class="form-control" placeholder="연락처를 입력하세요." aria-label="Username" aria-describedby="basic-addon1">
            </div>
        </div>
        <br>
        <div class="input-group contents">
            <span class="input-group-text">초대장 내용</span>
            <textarea class="form-control input-contents" 
            placeholder="초대장 내용을 입력하세요." 
            aria-label="With textarea"></textarea>
        </div>

    </b-card>
</template>

<script setup>

</script>

<style scoped>
    .card {
        background-color: #DDF5FF;
    }
    .np {
        display: flex;
        flex-direction: row;
        padding-left: 2rem;
        padding-right: 2rem;
        gap: 1rem;

    }    
    .contents {
        padding-left: 2rem;
        padding-right: 2rem;
    }
    .input-contents {
        width: 300px;  
        height: 150px; 
        resize: none; 
        display: flex;
        align-items: center;
    }
</style>