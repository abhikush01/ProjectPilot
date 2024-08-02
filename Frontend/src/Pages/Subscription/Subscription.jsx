import { useDispatch, useSelector } from "react-redux";
import SubscriptionCard from "./SubscriptionCard";
import { useEffect } from "react";
import { getUserSubscription } from "@/Redux/subscription/Action";

const paidPlan = [
  "Add unlimited project",
  "Access to live chat",
  "Add unlimited team member",
  "Advanced Reporting",
  "Priority Support",
  "Customization Options",
  "Integration Support",
  "Advanced Security",
  "Training and Resources",
  "Access Control",
  "Custom Workflows",
];

const annualPlan = [
  "Add unlimited project",
  "Access to live chat",
  "Add unlimited team member",
  "Advanced Reporting",
  "Priority Support",
  "Everything which montly plan has",
];

const freePlan = [
  "Add only 3 projects",
  "Basic Task Management",
  "Project Collaboration",
  "Basic Reporting",
  "Email Notification",
  "Basic Access Control",
];

function Subscription() {
  const { subscription } = useSelector((store) => store);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getUserSubscription());
  }, []);
  return (
    <div className="p-10">
      <h1 className="text-5xl font-semibold py-5 pb-16 text-center">Pricing</h1>
      <div className=" flex flex-col lg:flex-row justify-center items-center gap-9">
        <SubscriptionCard
          data={{
            planName: "Free",
            feature: freePlan,
            planType: "FREE",
            price: 0,
            buttonName:
              subscription.userSubsription?.planType === "FREE"
                ? "Current Plan"
                : "Get Started",
          }}
        />
        <SubscriptionCard
          data={{
            planName: "Monthly Paid Plan",
            feature: paidPlan,
            planType: "MONTHLY",
            price: 799,
            buttonName:
              subscription.userSubsription?.planType === "MONTHLY"
                ? "Current Plan"
                : "Get Started",
          }}
        />
        <SubscriptionCard
          data={{
            planName: "Annual Paid Plan",
            feature: annualPlan,
            planType: "ANNUAL",
            price: 6711,
            buttonName:
              subscription.userSubsription?.planType === "ANNUAL"
                ? "Current Plan"
                : "Get Started",
          }}
        />
      </div>
    </div>
  );
}

export default Subscription;
