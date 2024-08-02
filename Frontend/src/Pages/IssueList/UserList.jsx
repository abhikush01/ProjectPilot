import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { assignUserToIssue } from "@/Redux/Issue/Action";
import { useDispatch, useSelector } from "react-redux";

function UserList({ issueDetails }) {
  const { project } = useSelector((store) => store);
  const dispatch = useDispatch();
  const handleAssignIssueToUser = (userId) => {
    dispatch(assignUserToIssue({ issueId: issueDetails.id, userId }));
  };
  return (
    <>
      <div className="space-y-2">
        <div className="border rounded-md">
          <p className="py-2 px-3">
            {issueDetails.assignee?.fullname || "Unassigned"}
          </p>
        </div>
        {project.projectDetails?.team.map((item) => (
          <div
            onClick={() => handleAssignIssueToUser(item.id)}
            key={item.id}
            className="flex py-2 group hover:bg-slate-800 cursor-pointer items-center space-x-4 rounded-md border px-4"
          >
            <Avatar>
              <AvatarFallback>{item.fullname[0]}</AvatarFallback>
            </Avatar>
            <div className="space-y-1">
              <p className="text-sm leading-none">{item.fullname}</p>
              <p className="text-sm text-muted-foreground">{item.email}</p>
            </div>
          </div>
        ))}
      </div>
    </>
  );
}

export default UserList;
