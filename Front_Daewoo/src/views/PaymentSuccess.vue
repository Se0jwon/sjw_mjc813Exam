<template>
  <div>
    <h1>결제를 진행 중입니다...</h1>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { onMounted } from 'vue';
import axios from 'axios';

const route = useRoute();
const router = useRouter();

onMounted(async () => {
  const { paymentKey, orderId, amount } = route.query;

  try {
    // 백엔드로 결제 승인 요청 보내기
    const response = await axios.post('/api/payment/toss/confirm', {
      paymentKey,
      orderId,
      amount,
    });

    // 성공 시 로직
    console.log('결제 승인 성공:', response.data);
    alert('결제가 성공적으로 완료되었습니다.');
    // router.push('/order/complete'); // 실제 주문 완료 페이지로 이동

  } catch (error) {
    // 실패 시 로직
    console.error('결제 승인 실패:', error);
    const errorMessage = error.response?.data?.message || error.response?.data || '알 수 없는 오류가 발생했습니다.';
    router.push(`/payment/fail?message=${encodeURIComponent(errorMessage)}`);
  }
});
</script>