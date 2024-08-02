import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { deleteComment } from "@/Redux/Comment/Action";
import { TrashIcon } from "@radix-ui/react-icons";
import { useDispatch } from "react-redux";

function CommentCard({ comment }) {
  const dispatch = useDispatch();

  const handleDeleteComment = () => {
    dispatch(deleteComment(comment.id));
  };

  return (
    <div className="flex justify-between">
      <div className="flex items-center gap-4">
        <Avatar>
          <AvatarFallback>{comment.user.fullname[0]}</AvatarFallback>
        </Avatar>
        <div className="space-y-1">
          <p>{comment.user.fullname}</p>
          <p>{comment.content}</p>
        </div>
      </div>
      <Button
        onClick={handleDeleteComment}
        className="rounded-full"
        variant="ghost"
        size="icon"
      >
        <TrashIcon />
      </Button>
    </div>
  );
}

export default CommentCard;
