import { CompanyName, UserOptions, NavbarContainer, CompanyBranding, CompanyLogo } from "./styles"
import logo from "/vite.svg";

const Navbar = () => {
    return (
        <>
            <NavbarContainer>
                <CompanyBranding>
                    <CompanyLogo src={logo}></CompanyLogo>
                    <CompanyName>CompanyName</CompanyName>
                </CompanyBranding>
                <UserOptions>

                </UserOptions>
            </NavbarContainer>
        </>
    )
}

export default Navbar