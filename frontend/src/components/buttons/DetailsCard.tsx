// Em DetailsCard.tsx
interface DetailsCardProps {
    onOpenModal: () => void;
    bankName: string;
}

function DetailsCard({ onOpenModal, bankName }: DetailsCardProps) {
    return (
        <button onClick={onOpenModal} className="bg-green-500 rounded-sm max-w-25 w-20 max-h-30 font-normal p-2 text-white">
            <a>Details</a>
        </button>
    );
}

export default DetailsCard;
