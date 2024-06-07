import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import mobicredLogo from '../assets/mobicred.png';

interface NavLinkProps {
    to: string;
    label: string;
}

const Header = () => {
    const location = useLocation();
    const [activePage, setActivePage] = useState("");

    useEffect(() => {
        setActivePage(location.pathname.split('/')[1]); // Extrai o primeiro segmento da URL para determinar a página ativa
    }, [location.pathname]);

    const NavLink: React.FC<NavLinkProps> = ({ to, label }) => (
        <li>
            <Link
                to={to}
                onClick={() => setActivePage(to.split('/')[1])} // Atualiza a página ativa ao clicar no link
                className={`block py-2 px-3 text-gray-500 rounded m-2 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-green-700 md:p-2 font-bold ${activePage === to.split('/')[1] ? "active" : ""}`}
            >
                {label}
            </Link>
        </li>
    );
    const handleLogout = () => {
        localStorage.removeItem('token');
    };

    return (
        <div className="navbar flex items-center justify-between bg-white border border-gray-100 border-solid shadow-sm">
            <div className="flex items-center">
                <img src={mobicredLogo} alt="MobiCred Logo" style={{ maxWidth: "150px", height: "auto" }} />
            </div>
            <div className="flex justify-center flex-1">
                <ul className="flex flex-row space-x-5">
                    <NavLink to="/home" label="Overview" />
                    <NavLink to="/transactions" label="Transactions" />
                    <NavLink to="/goals" label="Goals" />
                    <NavLink to="/bank-accounts" label="Bank Accounts" />
                    <NavLink to="/calculators" label="Calculators" />
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
                        <li><Link to="/profile">Profile</Link></li>
                        <li><Link to="/settings">Settings</Link></li>
                        <li><Link to="/" onClick={handleLogout}>Logout</Link></li> {/* Adicionando evento onClick */}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default Header;