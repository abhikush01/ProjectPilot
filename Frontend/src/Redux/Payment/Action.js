import api from "@/config/api";

export const createPayment = async ({ planType }) => {
  try {
    const { data } = await api.post(`/api/payment/${planType}`);
    console.log(data);
    if (data.paymentLinkURL) {
      window.location.href = data.paymentLinkURL;
    }
  } catch (e) {
    console.log(e);
  }
};
