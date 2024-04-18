import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import CalendarIcon from "../assets/calendar.svg"
function HelloPeriod() {
    const [activeButton, setActiveButton] = React.useState("");
    const [startDate, setStartDate] = useState<Date | null>(null);

    const handleButtonClick = (label: string) => {
        setActiveButton(label);
    };

    return (
        <div className="flex justify-between items-center px-8 py-4 font-semibold max-md:px-5">
            <div className="text-3xl leading-10 text-gray-900">
                Hello, Laura!
            </div>

            <div className="flex gap-4 text-sm leading-5 text-slate-600">
                <div className="flex gap-0 rounded-lg border border-gray-100 border-solid">
                    <PeriodButton
                        label="This month"
                        activeButton={activeButton}
                        onButtonClick={handleButtonClick}
                    />
                    <PeriodButton
                        label="Last month"
                        activeButton={activeButton}
                        onButtonClick={handleButtonClick}
                    />
                    <PeriodButton
                        label="This year"
                        activeButton={activeButton}
                        onButtonClick={handleButtonClick}
                    />
                    <PeriodButton
                        label="Last 12 months"
                        activeButton={activeButton}
                        onButtonClick={handleButtonClick}
                    />
                </div>

                <div className="relative max-w-sm">
                    <div className="relative max-w-sm">
                        <div className="relative">
                            <DatePicker
                                selected={startDate}
                                onChange={(date: Date) => setStartDate(date)}
                                dateFormat="dd/MM/yyyy"
                                placeholderText="Select date"
                                maxDate={new Date(3000, 11, 31)}
                                className="bg-white border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 pr-8 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                            />
                            <div className="absolute inset-y-0 right-0 flex items-center pr-3 pointer-events-none">
                                <img
                                    src={CalendarIcon} // Use o ícone SVG importado aqui
                                    alt="Calendar Icon"
                                    className="w-6 h-6 text-gray-500 dark:text-gray-400"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

// Component for period selection button
interface PeriodButtonProps {
    label: string;
    activeButton: string;
    onButtonClick: (label: string) => void;
}

function PeriodButton({ label, activeButton, onButtonClick }: PeriodButtonProps) {
    const handleClick = () => {
        onButtonClick(label);
    };

    return (
        <button
            onClick={handleClick}
            className={`px-4 py-2 text-sm font-semibold ${activeButton === label
                ? "bg-[#F5FFF9] text-[#0B9055]"
                : "bg-white text-gray-700"
                }  border border-gray-100 border-solid focus:outline-none `}
        >
            {label}
        </button>
    );
}

export default HelloPeriod;
