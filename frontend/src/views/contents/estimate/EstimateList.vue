<template>
  <h1>받은 견적서</h1>
  <main>
    <div class="estimate-read">
      <p :style="{ textAlign: 'right' }">개수: {{ totalEstimate }}개</p>
      <div v-if="items.length">
        <b-table striped hover :items="items" :fields="fields">
          <template #cell(actions)="row">
            <b-button
              variant="outline-primary"
              @click="approveEstimate(row.item.id)"
              :disabled="row.item.status !== 'SENT'"
              >승인</b-button
            >
            <b-button
              variant="danger"
              @click="rejectEstimate(row.item.id)"
              :disabled="row.item.status !== 'SENT'"
              >거절</b-button
            >
          </template>
        </b-table>
      </div>
      <div v-else>데이터를 불러오는 중...</div>
    </div>
  </main>
</template>

<script setup>
import { ref, onMounted } from "vue";
//   import { useRouter } from "vue-router";

const items = ref([]);
const totalEstimate = ref(0);

const fields = [
  { key: "company_id", label: "해당 업체" },
  { key: "expected_cost", label: "예상 비용" },
  { key: "created_at", label: "작성 날짜" },
  { key: "status", label: "상태" },
  { key: "actions", label: "승인" },
];

const fetchEstimate = async () => {
  try {
    const estimate = await fetch("http://localhost:8888/estimate");
    if (!estimate.ok) {
      throw new Error("네트워크 응답이 올바르지 않습니다.");
    }
    const data = await estimate.json();
    items.value = data;
    totalEstimate.value = items.value.length;
  } catch (error) {
    console.error("견적서 데이터를 가져오는 중 오류 발생:", error);
  }
};
onMounted(() => {
  fetchEstimate();
});

// 견적서 승인
const approveEstimate = async (id) => {
  try {
    const response = await fetch(
      `http://localhost:8888/estimate/${id}/approve`,
      {
        method: "PUT",
      }
    );
    console.log(row.item.id);
    if (!response.ok) {
      throw new Error("견적서 승인 중 오류가 발생했습니다.");
    }
    items.value = items.value.map((item) => {
      if (item.id === id) {
        return { ...item, status: "CONFIRMED" };
      }
      return item;
    });
  } catch (error) {
    console.error("견적서 승인 중 오류 발생:", error);
  }
};
//     // item.status = "CONFIRMED";  // 상태 변함
//     await fetchEstimate();
//   } catch (error) {
//     console.error("견적서 승인 중 오류 발생:", error);
//   }
// };

// 견적서 거절
const rejectEstimate = async (id) => {
  try {
    const response = await fetch(
      `http://localhost:8888/estimate/${id}/reject`,
      {
        method: "DELETE",
      }
    );
    if (!response.ok) {
      throw new Error("견적서 거절 중 오류가 발생했습니다.");
    }
    // 목록에서 해당 견적서 삭제
    items.value = items.value.filter((item) => item.id !== id);
    totalEstimate.value = items.value.length;
  } catch (error) {
    console.error("견적서 거절 중 오류 발생:", error);
  }
};
</script>

<style scoped>
h1 {
  color: #49d5ff;
  font-family: Jua;
  margin: 60px;
}
main {
  padding: 20px;
  max-width: 80%;
  margin: auto;
}
.fields {
  text-align: inherit;
}
.button-container {
  display: flex;
  justify-content: center;
}
.button-container .b-button-group {
  align-items: center;
}
.button-container .custom-btn {
  display: block;
  margin: 20px auto;
  background-color: #fff;
  border: 2px solid #87ceeb;
  border-radius: 5px;
  color: #87ceeb;
  cursor: pointer;
}
.button-container .custom-btn.list-before,
.button-container .custom-btn.list-after {
  font-weight: bold;
}
</style>
