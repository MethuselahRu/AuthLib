package ru.methuselah.authlib.links;

public final class LinksMojang extends Links
{
	public LinksMojang()
	{
		super("",
			Links.MOJANG_A,
			Links.MOJANG_R,
			Links.MOJANG_V,
			Links.MOJANG_I,
			Links.MOJANG_S,
			Links.MOJANG_J,
			Links.MOJANG_HJ,
			Links.MOJANG_LJ,
			Links.MOJANG_LHJ,
			Links.MOJANG_API1,
			Links.MOJANG_API2,
			Links.MOJANG_API3,
			Links.MOJANG_API4);
		super.setProvider(Links.LinksProvider.mojang);
	}
}
