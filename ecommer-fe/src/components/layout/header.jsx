import React, { useState,useEffect } from 'react';
import Input from "../ui/input";
import Button from "../ui/button";
import { Link } from 'react-router-dom';
import { Search, ShoppingCart, User, Menu, X, Sun, Moon, Heart } from 'lucide-react';

const Header = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [isScrolled, setIsScrolled] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
          if (window.scrollY > 10) {
            setIsScrolled(true);
          } else {
            setIsScrolled(false);
          }
        };
    
        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    const handleSearch = (e) => {
        e.preventDefault();
        console.log("Đang tìm kiếm:", searchQuery);
    };


    return (
        <header className={`fixed md:px-45 top-0 left-0 right-0 w-full z-50 transition-all duration-300 ${isScrolled ? 'bg-white/90 backdrop-blur-sm shadow-sm' : 'bg-white'}`}>
            <div className="container mx-auto px-4">
                <div className="flex items-center justify-between h-16">
                    <Link to="/" className="flex items-center space-x-2">
                        <div className="w-8 h-8 bg-gradient-to-r bg-blue-500 to-accent rounded-lg flex items-center justify-center">
                        <span className="text-white font-bold text-lg">S</span>
                        </div>
                        <span className="font-bold text-xl bg-gradient-to-r text-blue-500 to-accent bg-clip-text ">
                        ShopVN
                        </span>
                    </Link>

                    <form onSubmit={handleSearch} className="hidden md:flex flex-1 max-w-md mx-8">
                        <div className="relative w-full">
                            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-muted-foreground w-4 h-4 text-gray-400" />
                            <Input
                                type="search"
                                placeholder="Tìm kiếm sản phẩm..."
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                                className="pl-10 pr-4 w-full"
                            />
                            </div>
                    </form>

                    <div className='flex items-center space-x-2'>
                        <Button className={`bg-transparent`}>
                            <Heart className="w-4 h-4 text-black" />
                        </Button>
                        <Button className={`bg-transparent`}>
                            <ShoppingCart className="w-4 h-4 text-black" />
                        </Button>
                        <Button className={`text-white`}>Đăng Nhập</Button>
                    </div>
                </div>

                <nav className="hidden md:flex items-center space-x-8 pb-4">
                    <Link to="/" className="text-sm font-medium hover:text-blue-400 transition-colors">
                        Trang chủ
                    </Link>
                    <Link to="/products" className="text-sm font-medium hover:text-blue-400 transition-colors">
                        Sản phẩm
                    </Link>
                    <Link to="/products?category=Điện thoại" className="text-sm font-medium hover:text-blue-400 transition-colors">
                        Điện thoại
                    </Link>
                    <Link to="/products?category=Laptop" className="text-sm font-medium hover:text-blue-400 transition-colors">
                        Laptop
                    </Link>
                    <Link to="/products?category=Tablet" className="text-sm font-medium hover:text-blue-400 transition-colors">
                        Tablet
                    </Link>
                    <Link to="/products?category=Phụ kiện" className="text-sm font-medium hover:text-blue-400 transition-colors">
                        Phụ kiện
                    </Link>
                </nav>

            </div>
        </header>

    );
}

export default Header;