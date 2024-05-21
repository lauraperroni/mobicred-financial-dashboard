import DeleteCard from '../buttons/DeleteCard';
import DetailsCard from '../buttons/DetailsCard';

interface BankCardProps {
    bankName: string;
    balance: number;
    nextBillingDate: string;
    billingBalance: number;
    onDelete?: () => void; // Tornando a propriedade onDelete opcional adicionando '?'
    onOpenModal: () => void;
}

function BankCard({ bankName, balance, nextBillingDate, billingBalance, onDelete, onOpenModal }: BankCardProps) {
    return (
        <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-80 w-72 m-4">
            <div className="bg-green-500 text-white px-4 py-2 p-8">
                <h2 className="text-xl font-semibold">Bank {bankName}</h2>
            </div>
            <div className="p-4">
                <div className="mb-2">
                    <span className="font-semibold text-gray-800">Balance:</span> $ {balance.toFixed(2)}
                </div>
                <div className="mb-2">
                    <span className="font-semibold text-gray-800">Due date:</span> {nextBillingDate}
                </div>
                <div>
                    <span className="font-semibold text-gray-800">Amount:</span> $ {billingBalance.toFixed(2)}
                </div>
                <div className="flex justify-between">
                    {/* Verifica se onDelete está definido antes de renderizar o componente DeleteCard */}
                    {onDelete && <DeleteCard onDelete={onDelete} />}
                    <DetailsCard onOpenModal={onOpenModal} bankName={bankName} />
                </div>
            </div>
        </div>
    );
}

export default BankCard;
