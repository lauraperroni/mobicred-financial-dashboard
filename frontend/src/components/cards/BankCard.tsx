import { BankAccountsService } from '../../services/Bank Accounts/BankAccountsService';
import DeleteCard from '../buttons/DeleteCard';
import DetailsCard from '../buttons/DetailsCard';

interface BankCardProps {
    id: number;
    bankName: string;
    balance: number;
    nextBillingDate: string;
    billingBalance: number;
    onDelete?: () => void; // Atualizado para não receber o ID como argumento
    onOpenModal: () => void;
}

function BankCard({ id, bankName, balance, nextBillingDate, billingBalance, onDelete, onOpenModal }: BankCardProps) {

    const handleDelete = async () => {
        try {
            await BankAccountsService.deleteBankAccounts(id);
            console.log('Conta bancária deletada com sucesso');
            // Se onDelete for definido, chame-o para executar qualquer ação adicional necessária após a exclusão
            if (onDelete) {
                onDelete();
            }
        } catch (error) {
            console.error('Erro ao deletar conta bancária:', error);
        }
    };

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
                    <span className="font-semibold text-gray-800">Amount:</span> $ {billingBalance ? billingBalance.toFixed(2) : ''}
                </div>
                <div className="flex justify-between">
                    {/* Verifica se onDelete está definido antes de renderizar o componente DeleteCard */}
                    {onDelete && <DeleteCard onDelete={handleDelete} />}
                    <DetailsCard onOpenModal={onOpenModal} bankName={bankName} />
                </div>
            </div>
        </div>
    );
}

export default BankCard;
