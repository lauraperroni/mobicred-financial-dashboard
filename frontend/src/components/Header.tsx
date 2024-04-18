import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import mobicredLogo from '../assets/mobicred.png';


const Header = () => {
    const location = useLocation();
    const [activePage, setActivePage] = useState("Overview");

    // Atualiza o estado activePage com base na rota atual
    useEffect(() => {
        switch (location.pathname) {
            case "/":
                setActivePage("Overview");
                break;
            case "/transactions":
                setActivePage("Transactions");
                break;
            case "/goals":
                setActivePage("Goals");
                break;
            case "/bank-accounts":
                setActivePage("Bank Accounts");
                break;
            case "/calculators":
                setActivePage("Calculators");
                break;
            default:
                setActivePage("Overview");
        }
    }, [location.pathname]);

    return (
        <div className="navbar flex items-center justify-between bg-white border border-gray-100 border-solid shadow-sm">

            <div className="flex items-center">
                <img src={mobicredLogo} alt="MobiCred Logo" style={{ maxWidth: "150px", height: "auto" }} />
            </div>

            <div className="flex justify-center flex-1">
                <ul className="flex flex-row space-x-5">
                    <li>
                        <a
                            href="/home"
                            onClick={() => setActivePage("Overview")}
                            className={`block py-2 px-3 text-gray-500 rounded m-2 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-green-700 md:p-2 font-bold ${activePage === "Overview" ? "active" : ""}`}
                        >
                            Overview
                        </a>
                    </li>
                    <li>
                        <a
                            href="/transactions"
                            onClick={() => setActivePage("Transactions")}
                            className={`block py-2 px-3 text-gray-500 rounded m-2 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-green-700 md:p-2 font-bold ${activePage === "Transactions" ? "active" : ""}`}
                        >
                            Transactions
                        </a>
                    </li>
                    <li>
                        <a
                            href="/goals"
                            onClick={() => setActivePage("Goals")}
                            className={`block py-2 px-3 text-gray-500 rounded m-2 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-green-700 md:p-2 font-bold ${activePage === "Goals" ? "active" : ""}`}
                        >
                            Goals
                        </a>
                    </li>
                    <li>
                        <a
                            href="/bank-accounts"
                            onClick={() => setActivePage("Bank Accounts")}
                            className={`block py-2 px-3 text-gray-500 rounded m-2 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-green-700 md:p-2 font-bold ${activePage === "Bank Accounts" ? "active" : ""}`}
                        >
                            Bank Accounts
                        </a>
                    </li>
                    <li>
                        <a
                            href="/calculators"
                            onClick={() => setActivePage("Calculators")}
                            className={`block py-2 px-3 text-gray-500 rounded m-2 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-green-700 md:p-2 font-bold ${activePage === "Calculators" ? "active" : ""}`}
                        >
                            Calculators
                        </a>
                    </li>
                </ul>
            </div>
            <div className="flex items-center">
                <div className="dropdown dropdown-end">
                    <div tabIndex={0} role="button" className="btn btn-ghost btn-circle avatar">
                        <div className="relative w-10 h-10 overflow-hidden bg-green-100 rounded-full dark:bg-gray-600">
                            <svg className="absolute w-12 h-12 text-black-400 -left-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fillRule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clipRule="evenodd"></path></svg>
                        </div>
                    </div>
                    <ul tabIndex={0} className="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
                        <li><a href="/profile">Profile</a></li>
                        <li><a>Settings</a></li>
                        <li><a>Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default Header;
