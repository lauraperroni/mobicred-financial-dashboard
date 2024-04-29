// Em DeleteCard.tsx
interface DeleteCardProps {
    onDelete: () => void; // Recebendo a função onDelete como propriedade
}

function DeleteCard({ onDelete }: DeleteCardProps) {
    return (
        <button className="max-w-25 max-h-30 font-normal text-green-400 pt-2 text-sm" onClick={onDelete}>
            Delete account
        </button>
    );
}

export default DeleteCard;
