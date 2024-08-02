import { Button } from "@/components/ui/button";
import { acceptProject } from "@/Redux/Project/Action";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

function AcceptInvitation() {
  const dispatch = useDispatch();
  const urlParams = new URLSearchParams(location.search);
  const token = urlParams.get("token");
  const navigate = useNavigate();
  const handleAcceptInvitation = () => {
    dispatch(acceptProject({ token, navigate }));
  };
  return (
    <div className="h-[85vh] flex flex-col justify-center items-center">
      <h1 className="py-5 font-semibold text-xl">
        you are invited to join the project
      </h1>
      <Button onClick={handleAcceptInvitation}>Accept Invitation</Button>
    </div>
  );
}

export default AcceptInvitation;
