<template>
    <h1>요청서 작성</h1>
    <form>
        <!-- 라디오 버튼 그룹 -->
        <b-form-group label="희망 이벤트" v-slot="{ ariaDescribedby }">
      <b-form-radio-group
        id="radio-slots"
        v-model="selected"
        :options="options"
        :aria-describedby="ariaDescribedby"
        name="category-options-slots"
      >
        <!-- Radios in this slot will appear first -->
        <template #first>
          <b-form-radio value="first">생일 이벤트</b-form-radio>
        </template>

        <!-- Radios in the default slot will appear after any option generated radios -->
        <b-form-radio :value="{ fourth: 4 }">This is the 4th radio</b-form-radio>
        <b-form-radio value="fifth">This is the 5th radio</b-form-radio>
      </b-form-radio-group>
    </b-form-group>


        <!-- <div v-for="(item, index) in radioList" :key="index">
            <input type="radio" :id="item.value" v-model="selectedEvent" :value="item.value" />
            <label :for="item.value">{{ item.value }}</label>
        </div> -->

        <!-- 선택된 이벤트에 따른 폼 -->
        <div v-if="selectedEvent">
            <p>희망 업체 <input type="text" v-model="formData.company" /></p>
            <p>희망 이벤트 <input type="text" v-model="formData.event" /></p>
            <p>희망 비용 <input type="text" v-model="formData.cost" /></p>
            <p>희망 장소 <input type="text" v-model="formData.location" /></p>
            <p>희망 시간 <input type="text" v-model="formData.time" /></p>
        </div>
    </form>

    <button @click="submitEvent">작성 완료</button>
</template>

<script>
export default {
    data() {
        return {
            radioList: [
                { value: "생일 이벤트" },
                { value: "장소 대관" }
            ],
            selectedEvent: "", // 선택한 이벤트 저장
            formData: {
                company: "",   // 희망 업체
                event: "",     // 희망 이벤트
                cost: "",      // 희망 비용
                location: "",  // 희망 장소
                time: ""       // 희망 시간
            }
        };
    },
    methods: {
        submitEvent() {
            // 폼 데이터 제출 로직
            console.log("폼 제출:", this.formData);
        }
    }
};
</script>

<style scoped>
h1 {
    color: rgb(255, 182, 193); /* 이미지와 일치하게 색상 변경 */
}
form {
    background-color: rgba(162, 218, 255, 0.384);
    padding: 20px;
    border-radius: 10px;
    max-width: 400px;
    margin: auto;
}
button {
    display: block;
    margin: 20px auto;
    padding: 10px 20px;
    border: 1px solid #ccc;
    background-color: white;
    border-radius: 5px;
    cursor: pointer;
}
</style>
